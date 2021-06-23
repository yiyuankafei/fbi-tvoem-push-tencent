package com.firstbrave.fbi.tvoem.tencent.bo;

import lombok.Data;

import java.util.List;

@Data
public class AlbumInfoBo {

    /**
     * 专辑 ID，非空用于指定专辑上传；空值则使用以下字段创建新专辑
     */
    private String cid;

    /**
     * 专辑标题
     */
    private String title;

    /**
     * 专辑封面信息
     */
    private List<ImageBo> images;

    /**
     * 专辑次级标题
     */
    private String secondTitle;

    /**
     * 专辑类型
     */
    private Integer cType;

    /**
     * 媒资子分类
     */
    private Integer cSubType;

    /**
     * 媒资品类
     */
    private Integer category;

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 栏目 ID
     */
    private Integer columnID;

    /**
     * 发布日期
     */
    private String publishDate;

    /**
     * 本期主题
     */
    private String subject;

}
