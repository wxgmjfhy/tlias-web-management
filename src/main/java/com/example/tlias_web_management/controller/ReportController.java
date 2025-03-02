package com.example.tlias_web_management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias_web_management.pojo.JobOption;
import com.example.tlias_web_management.pojo.Result;
import com.example.tlias_web_management.pojo.StudentCount;
import com.example.tlias_web_management.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /*
     * 员工职位统计
     * GET
     * http://localhost:8080/report/empJobData
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计各个职位的员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /*
     * 员工性别统计
     * GET
     * http://localhost:8080/report/empGenderData
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别信息");
        List<Map<String, Object> > genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /*
     * 班级人数统计
     * GET
     * http://localhost:8080/report/studentCountData
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        log.info("统计班级人数");
        StudentCount studentCount = reportService.getStudentCountData();
        return Result.success(studentCount);
    }

    /*
     * 学生学历统计
     * GET
     * http://localhost:8080/report/studentDegreeData
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("统计学生学历信息");
        List<Map<String, Object> > degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
}
