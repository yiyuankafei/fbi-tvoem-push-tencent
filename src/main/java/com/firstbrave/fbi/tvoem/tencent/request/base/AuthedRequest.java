package com.firstbrave.fbi.tvoem.tencent.request.base;

import lombok.Data;

@Data
public class AuthedRequest extends BaseRequest {

    /**
     * 合集上传 token,有效时间 12 小时
     */
    private String accessToken;

}
