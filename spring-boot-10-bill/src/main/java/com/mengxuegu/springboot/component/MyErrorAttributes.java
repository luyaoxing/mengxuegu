package com.mengxuegu.springboot.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @program: spring-boot-01-hello
 * @description: 自定义处理错误配置类,自定义数据处理信息进行响应
 * @author: Lunatic Princess
 * @create: 2019-02-02
 * Detailed time at 13:05
 **/
@Component  //向容器中添加该组件       以后在代码中看到 某个类继承了一个接口类，就说明这个类是这个接口的子类，
//                                        就相当于他了
public class MyErrorAttributes extends DefaultErrorAttributes {

    /**
     * 自定义数据进行响应
     * */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company", "mengxuegu.com");
        return map;
    }
}
