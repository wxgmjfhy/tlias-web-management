package com.example.tlias_web_management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tlias_web_management.mapper.EmpMapper;
import com.example.tlias_web_management.mapper.StudentMapper;
import com.example.tlias_web_management.pojo.JobOption;
import com.example.tlias_web_management.pojo.StudentCount;
import com.example.tlias_web_management.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object> > list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("job")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object> > getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public StudentCount getStudentCountData() {
        List<Map<String, Object> > list = studentMapper.countStudentClazzData();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new StudentCount(clazzList, dataList);
    }

    @Override
    public List<Map<String, Object> > getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }
}
