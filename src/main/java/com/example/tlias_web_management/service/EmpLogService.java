package com.example.tlias_web_management.service;

import com.example.tlias_web_management.pojo.EmpLog;

public interface EmpLogService {

    /*
     * 记录添加员工日志
     */
    public void insertLog(EmpLog empLog);
}
