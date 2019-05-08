package com.finalproject.springboot.service;

import com.finalproject.springboot.dto.Exposer;
import com.finalproject.springboot.dto.SeckillExecution;
import com.finalproject.springboot.entity.Seckill;
import com.finalproject.springboot.exception.RepeatKillException;
import com.finalproject.springboot.exception.SeckillCloseException;
import com.finalproject.springboot.exception.SeckillException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-03-05 10:13
 **/
public interface SeckillService {

    /**
     * 获取所有的秒杀商品列表
     *
     * @return
     */
    List<Seckill> findAll();

    /**
     * 获取某一条商品秒杀信息
     *
     * @param seckillId
     * @return
     */
    Seckill findById(Long seckillId);

    /**
     * 秒杀开始时输出暴露秒杀的地址
     * 否者输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀的操作
     *
     * @param seckillId
     * @param userPhone
     * @param money
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;
}
