package com.firstbrave.fbi.tvoem.tencent.request;

import com.firstbrave.fbi.tvoem.tencent.bo.AlbumInfoBo;
import com.firstbrave.fbi.tvoem.tencent.bo.VideoInfoBo;
import com.firstbrave.fbi.tvoem.tencent.request.base.AuthedRequest;
import lombok.Data;

import java.util.List;

@Data
public class UploadFinishRequest extends AuthedRequest {

    /**
     * 视频信息，详情如下
     */
    private List<VideoInfoBo> videoInfo;

    /**
     * 专辑信息，详情如下，不传则不会入媒资
     */
    private List<AlbumInfoBo> albumInfo;

    /**
     * 上传者
     */
    private String operator;

}
