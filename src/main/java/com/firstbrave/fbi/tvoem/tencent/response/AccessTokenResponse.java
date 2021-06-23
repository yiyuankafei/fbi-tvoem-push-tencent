package com.firstbrave.fbi.tvoem.tencent.response;

import com.firstbrave.fbi.tvoem.tencent.response.base.BaseResponse;
import lombok.Data;

@Data
public class AccessTokenResponse extends BaseResponse {

    /**
     * 合集上传 token,有效时间 12 小时
     */
    private String accessToken;

}
