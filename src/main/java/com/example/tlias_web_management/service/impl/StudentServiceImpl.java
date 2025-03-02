package com.example.tlias_web_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tlias_web_management.mapper.StudentMapper;
import com.example.tlias_web_management.pojo.PageResult;
import com.example.tlias_web_management.pojo.Student;
import com.example.tlias_web_management.pojo.StudentQueryParam;
import com.example.tlias_web_management.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        List<Student> studentList = studentMapper.list(studentQueryParam);

        @SuppressWarnings("resource")
        Page<Student> p = (Page<Student>) studentList;

        return new PageResult<Student>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Student student) {
        // 补全 createTime, updateTime
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.insert(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        // 补全修改时间
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.updateById(student);   
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void updateViolationDataById(Integer id, Integer score) {
        if (score == null) {
            throw new IllegalArgumentException("score cannot be null.");
        }

        Student student = studentMapper.getById(id);

        Short violationCount = student.getViolationCount();
        if (violationCount == null) {
            violationCount = 0;
        }
        student.setViolationCount((short) (violationCount + 1));
        
        Short violationScore = student.getViolationScore();
        if (violationScore == null) {
            violationScore = 0;
        }
        student.setViolationScore((short) (violationScore + score));

        student.setUpdateTime(LocalDateTime.now());

        studentMapper.updateById(student);
    }
}
