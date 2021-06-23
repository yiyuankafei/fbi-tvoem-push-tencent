package com.firstbrave.fbi.tvoem.tencent.bo;

import lombok.Data;

@Data
public class ImageBo {

    /**
     * 介质 ID，通过介质系统上传返回的唯一 ID
     */
    private Integer mediumID;

    /**
     * 图片类型 1:横图 0:竖图
     */
    private Integer pType;

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片高度
     */
    private Integer height;

}
