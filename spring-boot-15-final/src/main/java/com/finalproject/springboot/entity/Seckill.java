package com.finalproject.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: mengxuegu
 * ************************************************************************
 * @description: 秒杀商品的具体信息，需要考虑时间的先后顺序，排队和临时建表
 *                  秒杀商品表（秒杀商品表和其他商品表不同，属于独立的模块）
 * ************************************************************************
 * @author: Lunatic Princess
 * @create: 2019-04-21 21:52
 **/
public class Seckill implements Serializable {

    private long seckillId;                         //商品ID
    private String title;                           //商品标题
    private String image;                           //商品图片
    private BigDecimal price;                       //商品原价格
    private BigDecimal costPrice;                   //商品抢购价格

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /** 抢购开始 - 时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /** 抢购结束 - 时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /** 剩余库存数量 */
    private long stockCount;

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", costPrice=" + costPrice +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", stockCount=" + stockCount +
                '}';
    }

    public Seckill() {
    }

    public Seckill(long seckillId, String title, String image, BigDecimal price, BigDecimal costPrice, Date createTime, Date startTime, Date endTime, long stockCount) {
        this.seckillId = seckillId;
        this.title = title;
        this.image = image;
        this.price = price;
        this.costPrice = costPrice;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.stockCount = stockCount;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getStockCount() {
        return stockCount;
    }

    public void setStockCount(long stockCount) {
        this.stockCount = stockCount;
    }
}
