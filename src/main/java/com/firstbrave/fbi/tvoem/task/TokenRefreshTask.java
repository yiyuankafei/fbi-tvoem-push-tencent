package com.firstbrave.fbi.tvoem.task;

import com.firstbrave.fbi.tvoem.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenRefreshTask{

    @Autowired
    UploadService tencentService;

    /**
     * 预警函发送回调
     */
    @Scheduled(cron = "0 0 0-6 * * ? ")
    public void refreshToken() {
        tencentService.refreshToken();
    }



}
