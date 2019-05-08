package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: spring-boot-01-hello
 * @description: loginController
 * @author: Lunatic Princess
 * @create: 2019-01-27
 * Detailed time at 13:52
 **/

@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public String login(HttpSession session, String username, String password, Map<String, Object> map) {

        if (!StringUtils.isEmpty(username)
                && !StringUtils.isEmpty(password)){
            User user = userMapper.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)){
                //登录成功
                session.setAttribute("loginUser", user);
                //重定向：可以重定向到任意一个请求中（包括其他项目），地址栏改变
                //redirect
                return "redirect:/main.html";
            }
        }
        //登录失败
        map.put("msg", "用户名或密码错误");
        return "main/login";
//
//
//        //判断用户名不为空，密码是123，则登录成功,         下面要注意，123调用方法
//        //                                                如果password放在前面，就会出现空指针异常
//        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
//            session.setAttribute("loginUser", username);
//            //登录成功
//            //重定向：可以重定向到任意一个请求中（包括其他项目），地址栏改变
//            //redirect
//            return "redirect:/main.html";
//        }
//        //登录失败
//        map.put("msg", "用户名或密码错误");
//        return "main/login";
    }

    /**
     * 推出登录
     * */
    @GetMapping("/logout")
    public String logout(HttpSession session){
//        1。清空session中的用户信息
        session.removeAttribute("loginUser");
//        2.再将session进行注销
        session.invalidate();
//        3.返回登录页面   这个重定向指定的是一个请求路径，不是一个模板路径
        return "redirect:/index.html";
    }
}
