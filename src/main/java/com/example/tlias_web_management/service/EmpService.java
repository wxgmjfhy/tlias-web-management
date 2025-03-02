package com.example.tlias_web_management.service;

import java.util.List;

import com.example.tlias_web_management.pojo.Emp;
import com.example.tlias_web_management.pojo.EmpQueryParam;
import com.example.tlias_web_management.pojo.LoginInfo;
import com.example.tlias_web_management.pojo.PageResult;

public interface EmpService {
    
    /*
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /*
     * 新增员工
     */
    void save(Emp emp);

    /*
     * 批量删除员工
     */
    void deleteByIds(List<Integer> ids);

    /*
     * 根据 ID 查询员工的详细信息
     */
    Emp getInfo(Integer id);

    /*
     * 根据 ID 修改员工
     */
    void update(Emp emp);

    /*
     * 查询所有员工
     */
    List<Emp> findAll();

    /*
     * 员工登录
     */
    LoginInfo login(Emp emp);
}
