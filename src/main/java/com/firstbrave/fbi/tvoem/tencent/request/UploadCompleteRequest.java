package com.firstbrave.fbi.tvoem.tencent.request;

import com.firstbrave.fbi.tvoem.tencent.request.base.AuthedRequest;
import lombok.Data;

@Data
public class UploadCompleteRequest extends AuthedRequest {

    /**
     * 上传 ID/介质 ID，是介质系统的唯一标识
     */
    private Integer mediumID;

}
