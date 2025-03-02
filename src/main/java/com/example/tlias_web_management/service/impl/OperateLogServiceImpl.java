package com.example.tlias_web_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tlias_web_management.mapper.OperateLogMapper;
import com.example.tlias_web_management.pojo.OperateLog;
import com.example.tlias_web_management.pojo.PageResult;
import com.example.tlias_web_management.service.OperateLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<OperateLog> operateLogList = operateLogMapper.list();

        @SuppressWarnings("resource")
        Page<OperateLog> p = (Page<OperateLog>) operateLogList;

        return new PageResult<OperateLog>(p.getTotal(), p.getResult());
    }
}
