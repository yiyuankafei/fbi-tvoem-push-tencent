package com.firstbrave.fbi.tvoem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload.part")
@Data
public class UploadPartConfig {

    private Integer number;

    private Integer size;

    private Integer concurrent;

}
