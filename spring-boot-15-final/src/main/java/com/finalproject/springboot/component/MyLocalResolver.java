package com.finalproject.springboot.component;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @program: mengxuegu
 * @description: 国际化自定义组件，模拟浏览器Accept请求头信息，构建一个区域对象 Local
 * @author: Lunatic Princess
 * @create: 2019-04-23 22:42
 **/

public class MyLocalResolver implements LocaleResolver {

    //  解析区域信息,但是不清楚为什么这里需要执行两次。
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
//        获取自定义请求头部信息，与html页面的th:href(l='zh_CN')
        String l = httpServletRequest.getParameter("1");
        Locale locale = httpServletRequest.getLocale();
//        System.out.println(l + "!!!!!!!!!!!!!!1");
        //获取系统 默认的区域信息
//        Locale locale = Locale.getDefault();

        if (!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            //接受第1个参数为：   语言代码 ；    第2个参数为：   国家代码
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
