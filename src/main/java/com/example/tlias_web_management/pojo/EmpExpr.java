package com.example.tlias_web_management.pojo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmpExpr {
    private Integer id;
    private Integer empId;
    private LocalDate begin;
    private LocalDate end;
    private String company;
    private String job;
}
