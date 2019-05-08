package com.finalproject.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: mengxuegu
 * @description: 登录模块，主页点击我跳转进入登陆页面
 * @author: Lunatic Princess
 * @create: 2019-04-09 21:49
 **/
@Controller
public class LoginController {

    @GetMapping("/toPublic")
    public String toPublicPage(){
        return "main/public";
    }

    @GetMapping("/login")
    public String login(){
        return "main/login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, String username, String password, Map<String, Object> map){
        //判断用户名不为空且密码为123
        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
            session.setAttribute("loginUser", username);
            //登录成功
            return "redirect:/public.html";
        }
        System.out.println(username + password);

        //登录失败
        map.put("msg", "用户名或密码错误");
        return "redirect:/login.html";
    }

    @GetMapping("/logout")  //黜退登录
    public String logout(HttpSession session){
        //1.清空session
        session.removeAttribute("loginUser");
        //2.在进行注销
        session.invalidate();
        //3.返回登录页面
        return "redirect:/public.html";
    }
}
