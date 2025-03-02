package com.example.tlias_web_management.pojo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/*
 * 班级条件分页查询参数封装类
 */
@Data
public class ClazzQueryParam {

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
    private Integer page = 1;
    private Integer pageSize = 10;
}
