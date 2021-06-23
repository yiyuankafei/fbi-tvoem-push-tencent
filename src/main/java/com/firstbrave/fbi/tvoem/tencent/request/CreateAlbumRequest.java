package com.firstbrave.fbi.tvoem.tencent.request;

import com.firstbrave.fbi.tvoem.tencent.request.base.AuthedRequest;
import lombok.Data;

@Data
public class CreateAlbumRequest extends AuthedRequest {

    /**
     * 操作者
     */
    private String operator;

}
