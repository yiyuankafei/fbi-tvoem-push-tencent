package com.firstbrave.fbi.tvoem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadConfig {

    private String appid;

    private String encryptionKey;

    private String domain;

    private String operator;

    private Integer partNumber;

    private Long partSize;

    private Integer partConcurrent;

    private UploadMethodConfig method;

}
