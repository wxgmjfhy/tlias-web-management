package com.example.tlias_web_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias_web_management.anno.LogOperation;
import com.example.tlias_web_management.pojo.Emp;
import com.example.tlias_web_management.pojo.EmpQueryParam;
import com.example.tlias_web_management.pojo.PageResult;
import com.example.tlias_web_management.pojo.Result;
import com.example.tlias_web_management.service.EmpService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/*
 * 员工管理控制器
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /*
     * 条件分页查询
     * GET
     * http://localhost:8080/emps?name=张&gender=1&begin=2007-09-01&end=2022-09-01&page=1&pageSize=10
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("员工查询请求参数: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    
    /*
     * 添加员工
     * POST
     * http://localhost:8080/emps 请求体
     */
    @LogOperation
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("添加员工: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    /*
     * 批量删除员工
     * DELETE
     * http://localhost:8080/emps?ids=1,2,3
     */
    @LogOperation
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        if (ids.size() == 0) {
            log.info("未选择要删除的员工");
            return Result.error("请选择要删除的员工!");
        }
        log.info("批量删除员工: ids = {}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /*
     * 根据 ID 查询员工
     * GET
     * http://localhost:8080/emps/1
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据 ID 查询员工的详细信息: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /*
     * 根据 ID 修改员工
     * PUT
     * http://localhost:8080/emps 请求体
     */
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工: {}", emp);
        empService.update(emp);
        return Result.success();
    }

    /*
     * 查询所有员工
     * GET
     * http://localhost:8080/emps/list
     */
    @GetMapping("/list")
    public Result findAll() {
        log.info("查询所有员工");
        List<Emp> empList = empService.findAll();
        return Result.success(empList);
    }
}
