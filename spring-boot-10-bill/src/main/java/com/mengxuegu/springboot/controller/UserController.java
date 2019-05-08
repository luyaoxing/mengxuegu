package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.dao.ProviderDao;
import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @program: spring-boot-01-hello
 * @description: bill manage system, provider de controller
 * @author: Lunatic Princess
 * @create: 2019-01-26
 * Detailed time at 14:03
 **/
@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    //    @RequestMapping(value = "list",method = RequestMethod.GET);
//    SpringMvc的新版本特性，已经可以使用下面的更简洁的方法了。

    @Autowired
    UserMapper userMapper;

    @GetMapping("/users")
    public String list(Map<String, Object> map, User user) {
        logger.info("user列表查询。。。" + user);

        List<User> users = userMapper.getUsers(user);

        map.put("users", users);
        map.put("username", user.getUsername());
//        返回路径里不要带斜杠/ ，在源码里已经写了default-prefix前缀 classpath:/templates/，已经带了/
//                                              default-suffix后缀 .html，所以也不用带  .html。
        return "user/list";
    }

    /**
     * type = null 进入查看详情页面view.html
     * type=update则是进入update.html
     */


    //springmvc的内容，mapping里面的花括号，在方法参数里面同@PathVariable,获取provider里面的key pid
    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id,
                       @RequestParam(value = "type", defaultValue = "view") String type,
                       Map<String, Object> map) {
        logger.info("查询：" + id + " 的用户的详细信息");

        User user = userMapper.getUserById(id);
        map.put("user", user);

//        return "provider/view";
//        type=null的时候，进入view.html，type=update则是进入update.html
        return "user/" + type;
    }

    //修改供应商信息
    @PutMapping("/user")
    public String update(User user) {

        logger.info("更新用户信息。。。");
        //更新操作
        userMapper.updateUser(user);
        return "redirect:/users";
    }

    //前往添加供应商页面
    @GetMapping("/user")
    public String toAddPage() {
        return "user/add";
    }

    //添加供应商数据
    @PostMapping("/user")
    public String add(User user){
        logger.info("添加用户数据：" + user);
        //新增数据操作
        userMapper.addUser(user);
        return "redirect:/users";
    }

    //删除供应商
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Integer id) {
        logger.info("删除用户，id = " + id);
        userMapper.deleteUserById(id);
        /**
         * ******************************************************************
         * 重定向这里尤其注意！！重定向是mapping请求路径！！斜杠/也不能漏！！！
         * ******************************************************************
         * */
        return "redirect:/users";
    }

    @GetMapping("/user/pwd")
    public String toUpdatePwdPage(){
        return "main/password";
    }

    @ResponseBody  //不响应模板   直接返回json
    @GetMapping("/user/pwd/{oldPwd}")
    public Boolean checkPwd(HttpSession session, @PathVariable("oldPwd") String oldPwd){
        logger.info("旧密码： " + oldPwd);
        //1.从session中获取当前登录用户的User对象
        User user = (User) session.getAttribute("loginUser");

        if (user.getPassword().equals(oldPwd)){
            //输入的旧密码正确
            return true;
        }
        return false;
    }

    @PostMapping("/user/pwd")
    public String updatePwd(HttpSession session, String password){
        //1. 从Session中获取当前登录用户信息
        User user = (User) session.getAttribute("loginUser");
        //2. 更新密码
        user.setPassword(password);
        //3. 提交到数据库
        userMapper.updateUser(user);
        //4. 注销重新登录
        return "redirect:/index.html";
    }
}
