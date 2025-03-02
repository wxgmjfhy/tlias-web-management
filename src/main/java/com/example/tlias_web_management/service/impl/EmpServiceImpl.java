package com.example.tlias_web_management.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.tlias_web_management.mapper.EmpExprMapper;
import com.example.tlias_web_management.mapper.EmpMapper;
import com.example.tlias_web_management.pojo.Emp;
import com.example.tlias_web_management.pojo.EmpExpr;
import com.example.tlias_web_management.pojo.EmpLog;
import com.example.tlias_web_management.pojo.EmpQueryParam;
import com.example.tlias_web_management.pojo.LoginInfo;
import com.example.tlias_web_management.pojo.PageResult;
import com.example.tlias_web_management.service.EmpLogService;
import com.example.tlias_web_management.service.EmpService;
import com.example.tlias_web_management.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 设置分页参数 (PageHelper)
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        // 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        // 解析查询结果, 并封装
        @SuppressWarnings("resource")
        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional
    @Override
    public void save(Emp emp) {
        try {
            // 保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            // 保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                // 遍历集合, 为 empId 赋值
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));

                // 批量保存员工工作经历信息
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            // 记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }
    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        // 删除员工基本信息
        empMapper.deleteByIds(ids);
        
        // 删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        Emp emp = empMapper.getById(id);
        List<EmpExpr> exprList = empExprMapper.getByEmpId(id);
        emp.setExprList(exprList);
        return emp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        // 根据 ID 修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 根据 ID 修改员工工作经历信息 (可能有多条, 采用先删除, 后添加)

        // 删除旧的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId())); // 调用已有的方法

        // 添加新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId())); // 确保设置了员工的 id
            empExprMapper.insertBatch(exprList); // 调用已有的方法
        }
    }

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getInfoByUsernameAndPassword(emp);
        if (empLogin != null) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());

            String jwt = JwtUtils.generateJwt(dataMap);
            return new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
        }
        return null;
    }
}
