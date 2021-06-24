package com.firstbrave.fbi.tvoem.task;

import com.firstbrave.fbi.tvoem.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenRefreshTask{

    @Autowired
    UploadService tencentService;

    @Scheduled(cron = "0 0 */6 * * ? ")
    public void refreshToken() throws Exception {
        tencentService.refreshToken();
    }

}
