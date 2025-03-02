package com.example.tlias_web_management.service;

import java.util.List;

import com.example.tlias_web_management.pojo.PageResult;
import com.example.tlias_web_management.pojo.Student;
import com.example.tlias_web_management.pojo.StudentQueryParam;

public interface StudentService {

    /*
     * 分页查询
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /*
     * 新增学生
     */
    void save(Student student);

    /*
     * 根据 ID 查询学生
     */
    Student getById(Integer id);

    /*
     * 修改学生
     */
    void update(Student student);

    /*
     * 批量删除学生
     */
    void deleteByIds(List<Integer> ids);

    /*
     * 违纪处理
     */
    void updateViolationDataById(Integer id, Integer score);

}
