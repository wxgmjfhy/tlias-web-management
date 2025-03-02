package com.example.tlias_web_management.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.tlias_web_management.pojo.EmpLog;

@Mapper
public interface EmpLogMapper {
    
    /*
     * 插入日志
     */
    @Insert("insert into emp_log (operate_time, info) values (#{operateTime}, #{info})")
    public void insert(EmpLog empLog);
}
