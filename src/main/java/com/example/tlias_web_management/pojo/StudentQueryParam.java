package com.example.tlias_web_management.pojo;

import lombok.Data;

/*
 * 学生条件分页查询参数封装类
 */
@Data
public class StudentQueryParam {

    private String name;
    private Integer degree;
    private Integer clazzId;
    private Integer page = 1;
    private Integer pageSize = 10;
}
