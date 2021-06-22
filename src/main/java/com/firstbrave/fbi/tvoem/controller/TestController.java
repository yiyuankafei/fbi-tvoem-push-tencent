package com.firstbrave.fbi.tvoem.controller;

import com.alibaba.fastjson.JSON;
import com.firstbrave.fbi.tvoem.config.UploadPartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UploadPartConfig uploadPartConfig;

    @RequestMapping("/test")
    public String test(String name) {
        System.out.println(JSON.toJSONString(uploadPartConfig));
        return "test:" + name;
    }

}
