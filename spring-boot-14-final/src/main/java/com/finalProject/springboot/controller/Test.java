package com.finalProject.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: mengxuegu
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-04-08 12:55
 **/
@Controller
public class Test {

    @GetMapping("/aa")
    public String aa(){
        return "main/header";
    }
}
