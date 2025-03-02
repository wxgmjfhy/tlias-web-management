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
import com.example.tlias_web_management.pojo.Clazz;
import com.example.tlias_web_management.pojo.ClazzQueryParam;
import com.example.tlias_web_management.pojo.PageResult;
import com.example.tlias_web_management.pojo.Result;
import com.example.tlias_web_management.service.ClazzService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /*
     * 条件分页查询
     * GET
     * http://localhost:8080/clazzs?name=java&begin=2023-01-01&end=2023-06-30&page=1&pageSize=5
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("班级查询请求参数: {}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /*
     * 新增班级
     * POST
     * http://localhost:8080/clazzs 请求体
     */
    @LogOperation
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("新增班级: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /*
     * 根据 ID 查询班级
     * GET
     * http://localhost:8080/clazzs/8
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据 ID 查询班级: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /*
     * 修改班级
     * PUT
     * http://localhost:8080/clazzs
     */
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /*
     * 删除班级
     * DELETE
     * http://localhost:8080/clazzs/5
     */
    @LogOperation
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级: {}", id);
        clazzService.delete(id);
        return Result.success();
    }

    /*
     * 查询所有班级
     * GET
     * http://localhost:8080/clazzs/list
     */
    @GetMapping("/list")
    public Result findAll() {
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }
}
