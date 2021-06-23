package com.firstbrave.fbi.tvoem.tencent.response;


import com.firstbrave.fbi.tvoem.tencent.response.base.BaseResponse;
import lombok.Data;

@Data
public class InitUploadResponse extends BaseResponse {

    /**
     * 上传 ID/介质 ID，是介质系统的唯一标识
     */
    private Integer mediumID;

    /**
     * 对象 key
     */
    private String objectKey;

}
