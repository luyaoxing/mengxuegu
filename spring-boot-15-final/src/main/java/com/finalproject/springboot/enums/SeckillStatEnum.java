package com.finalproject.springboot.enums;

/**
 * @program: mengxuegu
 * @description: 枚举，一个构造器，一个静态实例枚举类，表示抢购可能遇到的各种状态，方便其他类直接使用，
 *                  直接判断使用。属于java的内省和反射机制
 * @author: Lunatic Princess
 * @create: 2019-04-22 10:24
 **/
public enum SeckillStatEnum {

    SUCCESS(1, "抢购成功"),
    END(0, "抢购已结束"),
    REPEAT_KILL(-1,"重复抢购"),
    INNER_ERROR(-2, "系统异常"),            //如果遇到未知错误，那就没办法了，直接标记直接抛出
    DATA_REWRITE(-3, "数据串改");           /** 但是不知道为什么，会出现非常多的莫名其妙的错误 */

    private int state;
    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatEnum stateOf(int index){
        for (SeckillStatEnum state : values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }

}
