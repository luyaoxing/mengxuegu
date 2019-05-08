package com.finalproject.springboot.controller;

import com.finalproject.springboot.dao.UserDao;
import com.finalproject.springboot.entity.Provider;
import com.finalproject.springboot.entity.User;
import com.finalproject.springboot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: mengxuegu
 * @description: 管理员权限的用户登录后 才有查看全部用户权力。可以看到全部的用户 crud
 * @author: Lunatic Princess
 * @create: 2019-04-19 12:39
 **/
//@RestController
@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    UserMapper userMapper;


    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/users")
    public String list(Map<String, Object> map, User user){
        logger.info("user列表查询。。。" + user);

        List<Provider> users = userMapper.getUser(user);

        map.put("users", users);

        return "user/list";
    }

    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id,
                       @RequestParam(value = "type", defaultValue = "view") String type,
                       Map<String, Object> map){

        logger.info("查询id= " + id + " 的用户的详细情况。");

        User users = userMapper.getUserById(id);

        map.put("user", users);

        return "user/" + type;
    }

    //前往添加yonghu页面
    @GetMapping("/user")
    public String toAddPage() {
        //前往添加供应商页面
        return "user/add";
    }

    //处理添加供应商请求
    @PostMapping("/user")
    public String addUser(User user) {
        logger.info("添加yonghu信息：" + user);

//        user.setBirthday(new Date());
        userMapper.addUser(user);

        return "redirect:/users";
    }

    //修改供应商信息
    @PutMapping("/user")
    public String update(User user){
        logger.info("更改用户信息。。。");
        //更新操作
        userMapper.updateUser(user);

        return "redirect:users";
    }

    //删除供应商
    @GetMapping("/duser/{id}")
    public String delete(@PathVariable("id") Integer id) {
        logger.info("删除用户，id = " + id);

        userMapper.deleteUserById(id);

        return "redirect:/users";
    }


}
