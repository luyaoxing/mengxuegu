package com.finalProject.springboot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: spring-boot-01-hello
 * @description: 定义登录拦截器，并没有添加到容器当中
 * @author: Lunatic Princess
 * @create: 2019-01-27
 * Detailed time at 14:59
 **/

public class LoginHandleInterceptor implements HandlerInterceptor {
    //调用目标方法之前被拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Object loginUser = request.getSession().getAttribute("loginUser");
//        if (loginUser != null){
            //放行
            return true;
//        }
        //没有登录过
//        request.setAttribute("msg", "没有权限，请先登录！");
//        request.getRequestDispatcher("/index.html").forward(request, response);
//        return false;
    }
}
