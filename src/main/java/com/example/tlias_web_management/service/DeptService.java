package com.example.tlias_web_management.service;

import java.util.List;

import com.example.tlias_web_management.pojo.Dept;

public interface DeptService {

    /*
     * 查询部门列表
     */
    List<Dept> findAll();

    /*
     * 根据 ID 删除部门
     */
    void deleteById(Integer id);

    /*
     * 新增部门
     */
    void add(Dept dept);

    /*
     * 根据 ID 查询部门
     */
    Dept getById(Integer id);

    /*
     * 根据 ID 修改部门
     */
    void update(Dept dept);

}
