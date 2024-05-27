package com.whj.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whj.utils.JwtHelper;
import com.whj.utils.Result;
import com.whj.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取请求头中的token
        String token = request.getHeader("token");
        //检查token是否有效
        boolean expiration = jwtHelper.isExpiration(token);
        if (!expiration) {
            return true;
        }
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);

        response.getWriter().print(json);

        return false;
    }
}
