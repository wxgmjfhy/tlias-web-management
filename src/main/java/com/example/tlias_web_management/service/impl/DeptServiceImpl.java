package com.example.tlias_web_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tlias_web_management.exception.AssociatedObjectsExistException;
import com.example.tlias_web_management.mapper.DeptMapper;
import com.example.tlias_web_management.mapper.EmpMapper;
import com.example.tlias_web_management.pojo.Dept;
import com.example.tlias_web_management.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        Integer countOfEmps = empMapper.countEmpsOfDeptByDeptId(id);

        if (countOfEmps != 0) {
            throw new AssociatedObjectsExistException("部门", "员工");
        }
        
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        // 补全基础属性 createTime, updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        // 调用 Mapper 接口方法
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {        
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        // 补全基础属性 updateTime
        dept.setUpdateTime((LocalDateTime.now()));

        // 调用 Mapper 接口方法
        deptMapper.update(dept);
    }
}
