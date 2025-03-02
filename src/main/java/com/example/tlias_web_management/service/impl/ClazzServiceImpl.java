package com.example.tlias_web_management.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.tlias_web_management.exception.AssociatedObjectsExistException;
import com.example.tlias_web_management.mapper.ClazzMapper;
import com.example.tlias_web_management.mapper.StudentMapper;
import com.example.tlias_web_management.pojo.Clazz;
import com.example.tlias_web_management.pojo.ClazzQueryParam;
import com.example.tlias_web_management.pojo.PageResult;
import com.example.tlias_web_management.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 设置分页参数 (PageHelper)
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        // 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        // 根据当前时间, 补全 status
        if (!CollectionUtils.isEmpty(clazzList)) {
            clazzList.forEach(clazz -> {
                if (LocalDate.now().isAfter(clazz.getEndDate())) {
                    clazz.setStatus("已结课");
                } else if (LocalDate.now().isBefore(clazz.getBeginDate())) {
                    clazz.setStatus("未开班");
                } else {
                    clazz.setStatus("在读中");
                }
            });
        }

        // 解析查询结果, 并封装
        @SuppressWarnings("resource")
        Page<Clazz> p = (Page<Clazz>) clazzList;

        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Clazz clazz) {
        // 补全基础属性 createTime, updateTime
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        // 补全修改时间
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.updateById(clazz);
    }

    @Override
    public void delete(Integer id) {
        Integer countOfStudents = studentMapper.countStudentsOfClazzByClazzId(id);

        if (countOfStudents != 0) {
            throw new AssociatedObjectsExistException("班级", "学生");
        }

        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
