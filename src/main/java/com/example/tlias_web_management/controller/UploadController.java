package com.example.tlias_web_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.tlias_web_management.anno.LogOperation;
import com.example.tlias_web_management.pojo.Result;
import com.example.tlias_web_management.utils.AliyunOSSOperator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /*
     * 上传文件 - 参数名 file
     * POST
     * http://localhost:8080/upload
     */
    @LogOperation
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传: {}", file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传 OSS, url: {}", url);
        return Result.success(url);
    }
}
