package com.example.tlias_web_management.service;

import java.util.List;
import java.util.Map;

import com.example.tlias_web_management.pojo.JobOption;
import com.example.tlias_web_management.pojo.StudentCount;

public interface ReportService {

    /*
     * 员工职位统计
     */
    JobOption getEmpJobData();

    /*
     * 员工性别统计
     */
    List<Map<String, Object>> getEmpGenderData();

    /*
     * 班级人数统计
     */
    StudentCount getStudentCountData();

    /*
     * 学生学历统计
     */
    List<Map<String, Object>> getStudentDegreeData();
}
