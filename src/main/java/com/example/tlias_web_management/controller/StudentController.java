package com.example.tlias_web_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias_web_management.anno.LogOperation;
import com.example.tlias_web_management.pojo.PageResult;
import com.example.tlias_web_management.pojo.Result;
import com.example.tlias_web_management.pojo.Student;
import com.example.tlias_web_management.pojo.StudentQueryParam;
import com.example.tlias_web_management.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*
     * 条件分页查询
     * GET
     * http://localhost:8080/students?name=张三&degree=1&clazzId=2&page=1&pageSize=5
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("学生条件查询参数: {}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /*
     * 新增学生
     * POST
     * http://localhost:8080/students 请求体
     */
    @LogOperation
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("新增学生: {}", student);
        studentService.save(student);
        return Result.success();
    }

    /*
     * 根据 ID 查询学生
     * GET
     * http://localhost:8080/students/8
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据 ID 查询学生: {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /*
     * 修改学生
     * PUT
     * http://localhost:8080/students 请求体
     */
    @LogOperation
    @PutMapping
    public Result udpate(@RequestBody Student student) {
        log.info("修改学生: {}", student);
        studentService.update(student);
        return Result.success();
    }

    /*
     * 批量删除学生
     * DELETE
     * http://localhost:8080/students/1,2,3
     */
    @LogOperation
    @DeleteMapping(value = {"/", "/{ids}"})
    public Result delete(@PathVariable(required = false) List<Integer> ids) {
        if (ids == null || ids.size() == 0) {
            log.info("未选择要删除的学生");
            return Result.error("请选择要删除的学生!");
        }
        log.info("批量删除学生: {}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /*
     * 违纪处理
     * PUT
     * http://localhost:8080/students/violation/5/5
     */
    @LogOperation
    @PutMapping("violation/{id}/{score}")
    public Result updateViolationData(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学生违纪处理, id: {}, score: {}", id, score);
        studentService.updateViolationDataById(id, score);
        return Result.success();
    }

}
