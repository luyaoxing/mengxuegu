package com.finalproject.springboot.exception;

/**
 * @program: mengxuegu
 * @description: 抢购关闭时出现异常，时间卡点时有可能出现
 * @author: Lunatic Princess
 * @create: 2019-04-23 21:49
 **/
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
