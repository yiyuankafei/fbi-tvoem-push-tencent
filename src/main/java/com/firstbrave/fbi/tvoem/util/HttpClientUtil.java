package com.firstbrave.fbi.tvoem.util;

import com.alibaba.fastjson.JSON;
import com.firstbrave.fbi.tvoem.tencent.request.base.BaseRequest;
import com.firstbrave.fbi.tvoem.tencent.response.base.BaseResponse;
import com.firstbrave.fbi.tvoem.tencent.response.base.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

@Slf4j
public class HttpClientUtil {

    public static <T> T exec(BaseRequest request, String url, Class<T> resultType) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity requestEntity = new StringEntity(JSON.toJSONString(request),"utf-8");
        requestEntity.setContentEncoding("UTF-8");
        log.info("请求地址:{},请求报文:{}", url, JSON.toJSONString(request));
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(requestEntity);
        String response = null;
        try {
            response = httpClient.execute(httpPost,responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("响应报文:{}",response);
        ResponseWrapper resultWrapper = JSON.parseObject(response, ResponseWrapper.class);
        if (resultWrapper.getCode() != 0) {
            //TODO 错误类型为token过期时，需刷新token重试请求
        }
        return JSON.parseObject(response, resultType);
    }

    public static void main(String[] args) {
        /*String filePath = "C:\\Users\\Administrator\\Desktop\\QQ图片20180912141028.png";
        String url = "http://localhost:5002/upload?mediumID=999&partNumber=1";
        file(url, filePath, 1, 1024 * 10);
        url = "http://localhost:5002/upload?mediumID=999&partNumber=2";
        file(url, filePath, 2, 1024 * 10);*/

    }

    public static BaseResponse file(String url, String filePath, Integer partNumber, Long partSize) {
        try {
            System.out.println("===url:" + url);
            RandomAccessFile accessFile = new RandomAccessFile(filePath, "r");
            System.out.println("seek:" + (partNumber - 1) * partSize);
            accessFile.seek((partNumber - 1) * partSize);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int length;
            while ((length = accessFile.read(bytes)) > 0 && bos.size() < partSize) {
                bos.write(bytes, 0, length);
            }
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/octet-stream");
            HttpEntity entity = new ByteArrayEntity(bos.toByteArray());
            post.setEntity(entity);

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
            response.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*public static BaseResponse file111() {
        try {
            byte[] b = new byte[1024];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
