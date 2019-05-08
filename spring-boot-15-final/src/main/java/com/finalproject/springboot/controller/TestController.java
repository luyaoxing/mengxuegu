package com.finalproject.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: mengxuegu
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-04-08 15:43
 **/
@Controller
public class TestController {

    @GetMapping("/test")
    public String test() {

        return "main/public";
    }
}
