package com.finalproject.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.finalproject.springboot.mapper")
@SpringBootApplication
public class SpringBoot15FinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot15FinalApplication.class, args);
    }

}
