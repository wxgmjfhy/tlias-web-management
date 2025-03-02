package com.example.tlias_web_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.tlias_web_management.pojo.OperateLog;

@Mapper
public interface OperateLogMapper {

    /*
     * 插入日志数据
     */
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    public void insert(OperateLog log);

    /*
     * 日志列表查询
     */
    @Select("select o.*, e.name operateEmpName from operate_log o left join emp e on o.operate_emp_id = e.id")
    public List<OperateLog> list();

}
