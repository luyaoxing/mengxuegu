package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot-10-bill
 * @description: AsyncController
 * @author: Lunatic Princess
 * @create: 2019-02-23
 * Detailed time at 21:28
 **/
@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/hello")
    public String hello(){
        asyncService.batchAdd();
        return "success";
    }
}
