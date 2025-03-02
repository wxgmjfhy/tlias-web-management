package com.example.tlias_web_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias_web_management.pojo.OperateLog;
import com.example.tlias_web_management.pojo.PageResult;
import com.example.tlias_web_management.pojo.Result;
import com.example.tlias_web_management.service.OperateLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/log")
@RestController
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    /*
     * 日志列表查询
     * GET
     * http://localhost:8080/log/page?page=1&pageSize=10
     */
    @GetMapping("/page")
    public Result page(@RequestParam Integer page, @RequestParam Integer pageSize) {
        log.info("日志列表查询, page: {}, pageSize: {}", page, pageSize);
        PageResult<OperateLog> pageResult = operateLogService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
