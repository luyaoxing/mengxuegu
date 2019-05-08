package com.finalproject.springboot.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: mengxuegu
 * @description: 登录的拦截器。防止没有登录就进入个人主页
 * @author: Lunatic Princess
 * @create: 2019-04-09 22:51
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            //已经登陆过,放行
            return true;
        }
        //没有登陆过
        request.setAttribute("msg", "没有权限，请先登录");
        //重定向不能携带信息，所以直接发送到页面
        request.getRequestDispatcher("/login.html").forward(request, response);
        return false;
    }
}
