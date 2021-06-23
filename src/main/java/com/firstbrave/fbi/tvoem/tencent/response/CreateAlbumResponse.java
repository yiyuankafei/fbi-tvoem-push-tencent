package com.firstbrave.fbi.tvoem.tencent.response;

import com.firstbrave.fbi.tvoem.tencent.response.base.BaseResponse;
import lombok.Data;

@Data
public class CreateAlbumResponse extends BaseResponse {

    /**
     * 合集 id
     */
    private Integer albumID;

}
