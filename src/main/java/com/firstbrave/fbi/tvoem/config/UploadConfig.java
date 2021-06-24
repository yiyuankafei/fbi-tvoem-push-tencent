package com.firstbrave.fbi.tvoem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {

    /**
     * 应用 id,区分来源
     */
    private String appid;
    /**
     * 访问密钥,使用 appkey 对字符串 'appid+appkey' 进行 AES 得到
     */
    private String encryptionKey;
    /**
     * 接口域名
     */
    private String domain;
    /**
     * 操作人员
     */
    private String operator;
    /**
     * 文件分片大小
     */
    private Long partSize;
    /**
     * 文件分片上传并发数
     */
    private Integer partConcurrent;
    /**
     * 接口地址
     */
    private UploadUrlConfig url;

}
