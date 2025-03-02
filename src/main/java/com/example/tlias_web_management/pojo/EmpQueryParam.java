package com.example.tlias_web_management.pojo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/*
 * 员工条件分页查询参数封装类
 */
@Data
public class EmpQueryParam {

    private Integer page = 1; // 默认
    private Integer pageSize = 10; // 默认
    private String name;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
