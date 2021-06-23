package com.firstbrave.fbi.tvoem.tencent.bo;

import lombok.Data;

import java.util.List;

@Data
public class VideoInfoBo {

    /**
     * 介质 ID，通过介质系统上传返回的唯一 ID
     */
    private Integer mediumID;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频次标题
     */
    private String secondTitle;

    /**
     * 视频简介
     */
    private String desc;

    /**
     * 视频标签
     */
    List<String> tags;

    /**
     * 视频业务类型
     */
    private Integer cType;

    /**
     * 栏目名称，需要唯一，上传新栏目前请和运营同事商定;无栏目的电视剧，动 漫节目等可不填
     */
    private String columnName;

    /**
     * 栏目 ID，需要唯一
     */
    private Integer columnID;

    /**
     * 专辑 ID，指定优先使用这个字段；未指定则使用 albumInfo 创建专辑并加入
     */
    private String coverID;

    /**
     * 专辑中的位置
     */
    private Integer pos;

    /**
     * 视频时长分类，0：长视频，即一期节目的完整视频;1-为从长视频中截断出 可选 来的短视频
     */
    private Integer clip;

    /**
     * 播出时间
     */
    private String playTime;

    /**
     * 完整版标识，1：完整版 0：非完整版
     */
    private Integer full;

}
