package com.finalproject.springboot.dto;

/**
 * @program: mengxuegu
 * @description: 这个类可以曝光用户url地址，防止同一个用户多次登录多测刷新页面，并行抢购
 * @author: Lunatic Princess
 * @create: 2019-04-22 09:53
 **/
public class Exposer {

    private boolean exposed;        //是否开启抢购  使即将抢购的商品暴露在用户页面上
    private String md5;             //抢购url地址加密，防止用户通过http网络抓包工具获取秒杀地址
    private long seckillId;         //被抢购商品id
    private long now;               //获取当前系统时间      yyyy-MM-dd HH-mm-ss  (毫秒)
    private long start;             //抢购开始时间
    private long end;               //抢购结束时间

    /**
     * 颗粒度设计
     * 曝光器，如果有用户抢购秒杀商品，就获取秒杀这个商品的用户的http url 地址
     * 主要作用是防止有同一个用户多次刷新多次登录后 并行操作，干扰其他用户的抢购机率
     * */
    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    //重载
    public Exposer(boolean exposed, Long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    //重载
    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    /**
     * 判断是否需要暴露抢购商品的用户的http url 地址
     * */
    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
