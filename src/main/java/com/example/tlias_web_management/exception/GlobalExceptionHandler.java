package com.example.tlias_web_management.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.tlias_web_management.pojo.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * 处理异常
     */
    @ExceptionHandler
    public Result handleException(Exception e) { // 形参指定能够处理的异常类型
        log.error("程序出错", e);
        return Result.error("出错, 请联系管理员");
    }

    /*
     * 处理违反"唯一键约束"的异常
     */
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }

    /*
     * 处理违反"被删除对象下有关联的对象时, 不能直接删除"的异常
     */
    @ExceptionHandler
    public Result handleAssociatedObjectsExistException(AssociatedObjectsExistException e) {
        log.error("程序出错", e);
        return Result.error(e.getMessage());
    }

    /*
     * 处理"参数不合法"的异常
     */
    @ExceptionHandler
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("程序出错", e);
        return Result.error(e.getMessage());
    }
}
