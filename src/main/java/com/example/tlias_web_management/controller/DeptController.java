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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias_web_management.anno.LogOperation;
import com.example.tlias_web_management.pojo.Dept;
import com.example.tlias_web_management.pojo.Result;
import com.example.tlias_web_management.service.DeptService;

import lombok.extern.slf4j.Slf4j;

/*
 * 部门管理控制器
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /*
     * 查询部门列表
     * GET
     * http://localhost:8080/depts
     */
    @GetMapping
    public Result list() {
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*
     * 根据 ID 删除部门
     * DELETE
     * http://localhost:8080/depts?id=1
     */
    @LogOperation
    @DeleteMapping()
    public Result delete(@RequestParam Integer id) {
        log.info("根据 ID 删除部门, id: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /*
     * 新增部门
     * POST
     * http://localhost:8080/depts 请求体
     */
    @LogOperation
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门, dept: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /*
     * 根据 ID 查询部门
     * GET
     * http://localhost:8080/depts/1
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据 ID 查询部门, id: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /*
     * 根据 ID 修改部门
     * PUT
     * http://localhost:8080/depts 请求体
     */
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("根据 ID 修改部门, dept: {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
