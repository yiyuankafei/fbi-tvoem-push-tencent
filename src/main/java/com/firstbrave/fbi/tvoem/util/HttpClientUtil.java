package com.firstbrave.fbi.tvoem.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.firstbrave.fbi.tvoem.tencent.request.base.BaseRequest;
import com.firstbrave.fbi.tvoem.tencent.response.base.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@Slf4j
public class HttpClientUtil {

    public static String exec(BaseRequest request, String url) {
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
        ResponseWrapper res = JSON.parseObject(response, new TypeReference<ResponseWrapper>(){});
        System.out.println(res.getCode());
        System.out.println("===");
        if (res.getCode() != 0) {
            log.error("");
            throw new RuntimeException("");
        }
        return response;
    }
}
