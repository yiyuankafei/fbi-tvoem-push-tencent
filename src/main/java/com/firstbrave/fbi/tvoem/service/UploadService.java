package com.firstbrave.fbi.tvoem.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.firstbrave.fbi.tvoem.config.UploadPartConfig;
import com.firstbrave.fbi.tvoem.tencent.request.AccessTokenRequest;
import com.firstbrave.fbi.tvoem.tencent.response.AccessTokenResponse;
import com.firstbrave.fbi.tvoem.tencent.response.base.ResponseWrapper;
import com.firstbrave.fbi.tvoem.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UploadService implements InitializingBean {

    private static String accessToken;

    @Autowired
    UploadPartConfig uploadPartConfig;

    public void refreshToken() {
        AccessTokenRequest request = new AccessTokenRequest();
        request.setAppid(uploadPartConfig.getAppid());
        request.setEncryptionKey(uploadPartConfig.getEncryptionKey());
        String execResult = HttpClientUtil.exec(request, uploadPartConfig.getDomain() + "/auth/getToken");
        ResponseWrapper<AccessTokenResponse> result = JSON.parseObject(execResult, new TypeReference<ResponseWrapper<AccessTokenResponse>>(){});
        accessToken = result.getData().getAccessToken();
    }

    @Override
    public void afterPropertiesSet() {
        refreshToken();
        log.info("初始化accessToken：" + accessToken);
    }
}
