package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot-10-bill
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-03-03
 * Detailed time at 23:29
 **/
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        return user;
    }

    @GetMapping("/updateUser")
    public User updateUser(User user){
        userService.updateUser(user);
        return user;
    }

    @GetMapping("/deleteUser/{id}")
    public Integer deleteUser(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
        return id;
    }



}
