package com.finalproject.springboot.dto;

import com.finalproject.springboot.entity.SeckillOrder;
import com.finalproject.springboot.enums.SeckillStatEnum;

/**
 * @program: mengxuegu
 * @description: 抢购开始之后，用户点击抢购后，执行抢购的过程 的 封装
 * @author: Lunatic Princess
 * @create: 2019-04-22 10:14
 **/
public class SeckillExecution {

    private long seckillId;             //被抢购商品id
     /** 有很多抢购的线程正在执行，这个是状态标志 */
    private int state;                  //抢购执行结果 的 状态
    private String stateInfo;           //状态执行的展示
    private SeckillOrder seckillOrder;  //抢购成功的订单对象，联表

    /** 状态执行：抢购商品参数作为标识位，标记该抢购过程的状态，最后是订单状态 */
    public SeckillExecution(Long seckillId, SeckillStatEnum seckillStatEnum, SeckillOrder seckillOrder) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
        this.seckillOrder = seckillOrder;
    }

    /** 方法重载，如果没有写入订单则直接使用该方法，在执行过程中需要用到【详情需要再看一遍桌面写好的思路】 */
    public SeckillExecution(Long seckillId, SeckillStatEnum seckillStatEnum) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SeckillOrder getSeckillOrder() {
        return seckillOrder;
    }

    public void setSeckillOrder(SeckillOrder seckillOrder) {
        this.seckillOrder = seckillOrder;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillOrder=" + seckillOrder +
                '}';
    }
}
