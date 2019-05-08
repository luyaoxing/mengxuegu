package com.finalproject.springboot.exception;

/**
 * @program: mengxuegu
 * @description: 异常总线
 * @author: Lunatic Princess
 * @create: 2019-04-23 21:46
 **/
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }
    /** 捕获信息 and 异常 ，去执行被重载的超类中的方法*/
    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
