package com.firstbrave.fbi.tvoem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadPartConfig {

    private String appid;

    private String encryptionKey;

    private String domain;

    private Integer partNumber;

    private Integer partSize;

    private Integer partConcurrent;

}
