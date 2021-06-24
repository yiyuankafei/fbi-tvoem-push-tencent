package com.firstbrave.fbi.tvoem.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test")
public class TestPo {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String c1;

    private String c2;

    private String c3;

    private String c4;

    private String c5;

    private String c6;

}
