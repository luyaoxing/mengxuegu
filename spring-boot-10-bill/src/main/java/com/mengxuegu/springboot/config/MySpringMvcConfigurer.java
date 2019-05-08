package com.mengxuegu.springboot.config;

import com.mengxuegu.springboot.component.MyLocalResolver;
import com.mengxuegu.springboot.interceptor.LoginHandleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: spring-boot-01-hello
 * @description: 这个config是为了地址栏直接访问8080可以到这个方法里面的地址。因为SpringBoot直接访问的是static下面的index页面。
 * @author: Lunatic Princess
 * @create: 2019-01-27
 * Detailed time at 10:49
 **/
//这个是配置类，需要这个注解进行标识
@Configuration

public class MySpringMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandleInterceptor())
                        //指定套拦截的请求,/**表示拦截所有请求。但是不能把主页什么的
                        //全部拦截，不然连登录都被拦截
                        .addPathPatterns("/**")
                        //排除不需要拦截的请求路径,第3个参数，/login是action的请求路径
                        .excludePathPatterns("/", "/index.html", "/login")
                        //springboot2.0之后需要将静态资源文件的访问路径  也排除
                        .excludePathPatterns("/css/*", "/img/*", "/js/*");
            }

            //添加视图控制器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/index.html").setViewName("main/login");
                registry.addViewController("/main.html").setViewName("main/index");
            }
        };
    }

    //区域解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }

}
