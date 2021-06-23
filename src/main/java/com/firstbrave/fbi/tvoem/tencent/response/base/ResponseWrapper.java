package com.firstbrave.fbi.tvoem.tencent.response.base;

import lombok.Data;

@Data
public class ResponseWrapper <T> {

    private Integer code;

    private String msg;

    private T data;

}
