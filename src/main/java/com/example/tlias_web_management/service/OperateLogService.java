package com.example.tlias_web_management.service;

import com.example.tlias_web_management.pojo.OperateLog;
import com.example.tlias_web_management.pojo.PageResult;

public interface OperateLogService {

    /*
     * 日志列表查询
     */
    PageResult<OperateLog> page(Integer page, Integer pageSize);

}
