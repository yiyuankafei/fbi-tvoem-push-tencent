package com.firstbrave.fbi.tvoem.tencent.request;

import com.firstbrave.fbi.tvoem.tencent.request.base.AuthedRequest;
import lombok.Data;

@Data
public class InitUploadRequest extends AuthedRequest {

    /**
     * 文件大小，有值会进行校验
     */
    private Integer size;

    /**
     * 文件 md5，有值会进行校验
     */
    private String md5;

    /**
     * 合集 ID，聚合专辑的 ID
     */
    private Integer albumID;

    /**
     * 操作者
     */
    private String operator;

}
