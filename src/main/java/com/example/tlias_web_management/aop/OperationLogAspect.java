package com.example.tlias_web_management.aop;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.tlias_web_management.anno.LogOperation;
import com.example.tlias_web_management.mapper.OperateLogMapper;
import com.example.tlias_web_management.pojo.OperateLog;
import com.example.tlias_web_management.utils.CurrentHolder;

import lombok.extern.slf4j.Slf4j;

@Aspect // 当前类是切面类
@Slf4j
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(logOperation)")
    public Object around(ProceedingJoinPoint joinPoint, LogOperation logOperation) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        long costTime = endTime - startTime;

        OperateLog operateLog = new OperateLog();
        
        operateLog.setOperateEmpId(CurrentHolder.getCurrentId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(costTime);

        log.info("记录操作日志: {}", operateLog);
        operateLogMapper.insert(operateLog);

        return result;
    };

}
