package com.example.tlias_web_management.interceptor;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.tlias_web_management.utils.CurrentHolder;
import com.example.tlias_web_management.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        // 获取请求 url
        String url = request.getRequestURL().toString();

        // 判断 url 是否含 login, 如果包含说明是登录操作, 放行
        if (url.contains("login")) {
            log.info("登录请求, 放行");
            return true;
        }

        // 获取请求 token
        String jwt = request.getHeader("token");

        // 判断 token 是否存在, 如果不存在, 返回错误结果 (未登录)
        if (!StringUtils.hasLength(jwt)) {
            log.info("获取到 jwt 令牌为空, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }

        // 解析 token, 如果解析失败, 返回错误结果 (未登录)
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            log.info("当前线程绑定员工 ID: {}", empId);
            CurrentHolder.setCurrentId(empId); // 存入员工 ID
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }

        log.info("令牌合法, 放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        CurrentHolder.remove();
        log.info("方法执行完毕, 清空当前线程绑定的员工 ID");
    }
}
