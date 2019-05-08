package com.mengxuegu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: mengxuegu
 * @description: test
 * @author: Lunatic Princess
 * @create: 2019-04-08 19:13
 **/
@Controller
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test/public";
    }
}
