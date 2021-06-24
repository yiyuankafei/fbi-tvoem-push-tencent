package com.firstbrave.fbi.tvoem.service;

import com.firstbrave.fbi.tvoem.config.UploadConfig;
import com.firstbrave.fbi.tvoem.tencent.request.*;
import com.firstbrave.fbi.tvoem.tencent.response.AccessTokenResponse;
import com.firstbrave.fbi.tvoem.tencent.response.CreateAlbumResponse;
import com.firstbrave.fbi.tvoem.tencent.response.InitUploadResponse;
import com.firstbrave.fbi.tvoem.tencent.response.base.BaseResponse;
import com.firstbrave.fbi.tvoem.util.HttpClientUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class UploadService implements InitializingBean {

    private static String accessToken;

    private ExecutorService threadPool = Executors.newFixedThreadPool(5);

    @Autowired
    UploadConfig uploadConfig;

    /**
     *
     * 刷新token
     */
    public void refreshToken() throws Exception {
        AccessTokenRequest request = new AccessTokenRequest();
        request.setAppid(uploadConfig.getAppid());
        request.setEncryptionKey(uploadConfig.getEncryptionKey());
        AccessTokenResponse result = HttpClientUtil.exec(request, uploadConfig.getDomain() + uploadConfig.getMethod().getAccessToken(), AccessTokenResponse.class);
        accessToken = result.getAccessToken();
    }

    /**
     *
     * 创建专辑
     */
    public Integer createAlbum() throws Exception {
        CreateAlbumRequest request = new CreateAlbumRequest();
        request.setAppid(uploadConfig.getAppid());
        request.setOperator(uploadConfig.getOperator());
        request.setAccessToken(accessToken);
        CreateAlbumResponse result = HttpClientUtil.exec(request, uploadConfig.getDomain() + uploadConfig.getMethod().getCreateAlbum(), CreateAlbumResponse.class);
        return result.getAlbumID();
    }

    /**
     *
     * 初始化上传
     */
    public Integer initUpload(Integer size, String md5, Integer albumID) throws Exception {
        InitUploadRequest request = new InitUploadRequest();
        request.setAppid(uploadConfig.getAppid());
        request.setAccessToken(accessToken);
        request.setOperator(uploadConfig.getOperator());
        request.setAlbumID(albumID);
        request.setSize(size);
        request.setMd5(md5);
        InitUploadResponse result = HttpClientUtil.exec(request, uploadConfig.getDomain() + uploadConfig.getMethod().getInitUpload(), InitUploadResponse.class);
        return result.getMediumID();
    }

    /**
     *
     * 上传
     */
    public void upload(String filePath, Integer mediumID) throws Exception {
        File file = new File(filePath);
        Long length = file.length();
        Long partNumber = length % uploadConfig.getPartSize() == 0 ? length / uploadConfig.getPartSize() : length / uploadConfig.getPartSize() + 1;
        CountDownLatch countDownLatch = new CountDownLatch(partNumber.intValue());
        for (int i = 1; i <= partNumber; i++) {
            int currentPart = i;
            UploadThread thread = new UploadThread(filePath, mediumID, currentPart, countDownLatch);
            threadPool.submit(thread);
        }
        countDownLatch.await();
        uplpadComplete(mediumID);
    }

    /**
     *
     * 上传完成
     */
    public void uplpadComplete(Integer mediumID) throws Exception {
        UploadCompleteRequest request = new UploadCompleteRequest();
        request.setAppid(uploadConfig.getAppid());
        request.setAccessToken(accessToken);
        request.setMediumID(mediumID);
        HttpClientUtil.exec(request, uploadConfig.getDomain() + uploadConfig.getMethod().getUploadComplete(), BaseResponse.class);
    }

    /**
     *
     * 添加视频专辑
     */
    public void addAlbum(AddAlbumRequest request) throws Exception {
        request.setAppid(uploadConfig.getAppid());
        request.setAccessToken(accessToken);
        request.setOperator(uploadConfig.getOperator());
        HttpClientUtil.exec(request, uploadConfig.getDomain() + uploadConfig.getMethod().getAddAlbum(), BaseResponse.class);
    }

    @Data
    @AllArgsConstructor
    class UploadThread implements Runnable {

        String filePath;

        Integer mediumID;

        Integer partNumber;

        CountDownLatch latch;

        @Override
        public void run() {
            try {
                String requestUrl = uploadConfig.getDomain() + String.format(uploadConfig.getMethod().getUpload(),
                        uploadConfig.getAppid(), accessToken, mediumID, partNumber);
                System.out.println("************************");
                System.out.println(requestUrl);
                HttpClientUtil.uploadPart(requestUrl, filePath, partNumber, uploadConfig.getPartSize(),
                        mediumID, uploadConfig.getAppid(), accessToken);
            } finally {
                latch.countDown();
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        //refreshToken();
        log.info("初始化accessToken：" + accessToken);
    }
}
