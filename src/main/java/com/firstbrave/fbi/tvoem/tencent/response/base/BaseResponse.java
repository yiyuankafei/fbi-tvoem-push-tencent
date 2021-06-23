package com.firstbrave.fbi.tvoem.tencent.response.base;

import lombok.Data;

@Data
public class BaseResponse {

        /**
         * 返回码
         */
        private Integer code;

        /**
         * 接口信息
         */
        private String msg;

        /**
         * 请求 id，用于链路追踪
         */
        private String requestID;

}
