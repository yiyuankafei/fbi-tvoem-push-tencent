package com.firstbrave.fbi.tvoem.pojo.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadPartBo {

    private String appid;

    private String accessToken;

    private String url;

    private String filePath;

    private Integer mediumID;

    private Integer partNumber;

    private Long partSize;

}
