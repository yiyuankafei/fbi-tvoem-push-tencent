package com.firstbrave.fbi.tvoem.util;

import com.alibaba.fastjson.JSON;
import com.firstbrave.fbi.tvoem.tencent.request.base.BaseRequest;
import com.firstbrave.fbi.tvoem.tencent.response.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.RandomAccessFile;

@Slf4j
public class HttpClientUtil {

    public static <T extends BaseResponse> T exec(BaseRequest request, String url, Class<T> resultType) throws Exception {
        log.info("请求地址:{},请求报文:{}", url, JSON.toJSONString(request));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity requestEntity = new StringEntity(JSON.toJSONString(request),"utf-8");

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(requestEntity);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String response = httpClient.execute(httpPost, responseHandler);
        log.info("响应报文:{}",response);
        T result = JSON.parseObject(response, resultType);
        if (result.getCode() != 0) {
            //TODO 错误码为token过期，刷新token重试请求
            throw new RuntimeException(result.getMsg());
        }
        return result;
    }

    public static void uploadPart(String url, String filePath, Integer partNumber, Long partSize, Integer mediumID, String appid, String accessToken) throws Exception {
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        log.info("上传分片请求地址:{}, 文件名:{},分片编号:{}", url, filePath, partNumber);
        RandomAccessFile accessFile = new RandomAccessFile(filePath, "r");
        accessFile.seek((partNumber - 1) * partSize);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length;
        while ((length = accessFile.read(bytes)) > 0 && bos.size() < partSize) {
            bos.write(bytes, 0, length);
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("content", bos.toByteArray(), ContentType.DEFAULT_BINARY, partNumber + "_" + fileName);
        multipartEntityBuilder.addPart("appid",new StringBody(appid, ContentType.TEXT_PLAIN));
        multipartEntityBuilder.addPart("accessToken",new StringBody(accessToken, ContentType.TEXT_PLAIN));
        multipartEntityBuilder.addPart("mediumID", new StringBody(String.valueOf(mediumID), ContentType.TEXT_PLAIN));
        multipartEntityBuilder.addPart("partNumber",new StringBody(String.valueOf(partNumber), ContentType.TEXT_PLAIN));
        HttpEntity entity = multipartEntityBuilder.build();

        HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String response = httpClient.execute(post, responseHandler);
        log.info("响应报文:{}",response);
        BaseResponse result = JSON.parseObject(response, BaseResponse.class);
        if (result.getCode() != 0) {
            //TODO 错误码为token过期，刷新token重试请求
            throw new RuntimeException(result.getMsg());
        }
    }

}
