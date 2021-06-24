package com.firstbrave.fbi.tvoem.task;

import com.firstbrave.fbi.tvoem.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenRefreshTask{

    @Autowired
    UploadService tencentService;

    @Scheduled(cron = "0 0 */6 * * ? ")
    public void refreshToken() throws Exception {
        log.info("刷新token");
        tencentService.refreshToken();
    }

}
