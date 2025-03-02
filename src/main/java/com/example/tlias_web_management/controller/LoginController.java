package com.example.tlias_web_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias_web_management.pojo.Emp;
import com.example.tlias_web_management.pojo.LoginInfo;
import com.example.tlias_web_management.pojo.Result;
import com.example.tlias_web_management.service.EmpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /*
     * 员工登录
     * POST
     * http://localhost:8080/login
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工请求登录: {}", emp);
        LoginInfo loginInfo = empService.login(emp);
        if (loginInfo != null) {
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }
}
