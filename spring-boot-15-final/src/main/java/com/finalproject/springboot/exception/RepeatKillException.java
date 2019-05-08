package com.finalproject.springboot.exception;

/**
 * @program: mengxuegu
 * @description: 在执行过程中又点了一次抢购时出现的重复抢购的异常，判定结果都提交给枚举类进行执行状态标识。
 *                  显著例子：廉颇大招飞向地方，遇到宫本武藏的一技能时，判定机制对于碰到宫本一技能时的是否为飞行物的判定
 * @author: Lunatic Princess
 * @create: 2019-04-23 21:51
 **/
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
