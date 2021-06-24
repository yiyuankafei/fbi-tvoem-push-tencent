package com.firstbrave.fbi.tvoem.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.firstbrave.fbi.tvoem.config.UploadConfig;
import com.firstbrave.fbi.tvoem.po.TestPo;
import com.firstbrave.fbi.tvoem.service.TestService;
import com.firstbrave.fbi.tvoem.service.UploadService;
import com.firstbrave.fbi.tvoem.tencent.request.AddAlbumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    UploadConfig uploadPartConfig;

    @Autowired
    UploadService uploadService;

    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public List<TestPo> test() {
        QueryWrapper<TestPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().gt(TestPo::getId, 1);
        List<TestPo> list = testService.list(queryWrapper);
        return list;
    }

    @RequestMapping("/createAlbum")
    public Integer createAlbum() throws Exception {
        return uploadService.createAlbum();
    }

    @RequestMapping("/initUpload")
    public Integer createAlbum(Integer size, String md5, Integer albumID) throws Exception {
        return uploadService.initUpload(size, md5, albumID);
    }

    @RequestMapping("/upload")
    public void upload() throws Exception {
        String filePath = "C:\\Users\\Administrator\\Desktop\\b21bb051f81986186425bf0bf098b57b8ad4e659.jpeg";
        uploadService.upload(filePath, 12345678);
    }

    @RequestMapping("/uploadComplete")
    public void uplpadComplete(Integer mediumID) throws Exception {
        uploadService.uplpadComplete(mediumID);
    }

    @RequestMapping("/addAlbum")
    public void addAlbum(@RequestBody AddAlbumRequest request) throws Exception {
        uploadService.addAlbum(request);
    }

}
