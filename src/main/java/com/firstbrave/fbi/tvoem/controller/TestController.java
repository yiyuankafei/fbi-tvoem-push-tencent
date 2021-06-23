package com.firstbrave.fbi.tvoem.controller;

import com.alibaba.fastjson.JSON;
import com.firstbrave.fbi.tvoem.config.UploadConfig;
import com.firstbrave.fbi.tvoem.service.UploadService;
import com.firstbrave.fbi.tvoem.tencent.request.AddAlbumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UploadConfig uploadPartConfig;

    @Autowired
    UploadService uploadService;

    @RequestMapping("/config")
    public String test() {
        return JSON.toJSONString(uploadPartConfig);
    }

    @RequestMapping("/createAlbum")
    public Integer createAlbum(String name) {
        return uploadService.createAlbum();
    }

    @RequestMapping("/initUpload")
    public Integer createAlbum(Integer size, String md5, Integer albumID) {
        return uploadService.initUpload(size, md5, albumID);
    }

    @RequestMapping("/uploadComplete")
    public void uplpadComplete(Integer mediumID) {
        uploadService.uplpadComplete(mediumID);
    }

    @RequestMapping("/addAlbum")
    public void addAlbum(@RequestBody AddAlbumRequest request) {
        uploadService.addAlbum(request);
    }

    @RequestMapping("/upload")
    public void upload() throws Exception {
        String filePath = "C:\\Users\\Administrator\\Desktop\\新建文档 .txt";
        uploadService.upload(filePath, 111);
    }

}
