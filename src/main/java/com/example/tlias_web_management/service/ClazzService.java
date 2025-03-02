package com.example.tlias_web_management.service;

import java.util.List;

import com.example.tlias_web_management.pojo.Clazz;
import com.example.tlias_web_management.pojo.ClazzQueryParam;
import com.example.tlias_web_management.pojo.PageResult;

public interface ClazzService {

    /*
     * 分页查询
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /*
     * 新增班级
     */
    void save(Clazz clazz);

    /*
     * 根据 ID 查询班级
     */
    Clazz getById(Integer id);

    /*
     * 根据 ID 修改班级
     */
    void update(Clazz clazz);

    /*
     * 根据 ID 删除班级
     */
    void delete(Integer id);

    /*
     * 查询所有班级
     */
    List<Clazz> findAll();

}
