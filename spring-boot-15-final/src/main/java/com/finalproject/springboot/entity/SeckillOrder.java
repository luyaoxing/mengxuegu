package com.finalproject.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: mengxuegu
 * ***************************************************************************
 * @description: 秒杀商品的具体信息，需要考虑时间的先后顺序，排队和临时建表
 *  *                  秒杀商品表（秒杀商品表和其他商品表不同，属于独立的模块）
 *  ***************************************************************************
 * @author: Lunatic Princess
 * @create: 2019-04-21 21:56
 **/
public class SeckillOrder implements Serializable {

    private long seckillId;                 //抢购到的商品ID
    private BigDecimal money;               //支付金额
    private long userPhone;                 //抢购用户的手机号

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;                //创建时间

    private boolean status;                 //订单状态， -1:无效 0:成功 1:已付款

    private Seckill seckill;                //被抢购商品，和订单是一对多的关系

    @Override
    public String toString() {
        return "SeckillOrder{" +
                "seckillId=" + seckillId +
                ", money=" + money +
                ", userPhone=" + userPhone +
                ", createTime=" + createTime +
                ", status=" + status +
                ", seckill=" + seckill +
                '}';
    }

    public SeckillOrder() {
    }

    public SeckillOrder(long seckillId, BigDecimal money, long userPhone, Date createTime, boolean status, Seckill seckill) {
        this.seckillId = seckillId;
        this.money = money;
        this.userPhone = userPhone;
        this.createTime = createTime;
        this.status = status;
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }
}
