package com.finalproject.springboot.service.impl;

import com.finalproject.springboot.dto.Exposer;
import com.finalproject.springboot.dto.SeckillExecution;
import com.finalproject.springboot.entity.Seckill;
import com.finalproject.springboot.entity.SeckillOrder;
import com.finalproject.springboot.enums.SeckillStatEnum;
import com.finalproject.springboot.exception.RepeatKillException;
import com.finalproject.springboot.exception.SeckillCloseException;
import com.finalproject.springboot.exception.SeckillException;
import com.finalproject.springboot.mapper.SeckillMapper;
import com.finalproject.springboot.mapper.SeckillOrderMapper;
import com.finalproject.springboot.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: mengxuegu
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-05-05 20:14
 **/
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /** 1. 设置盐值字符串
     *  2. 混淆md5值，直接显著增加破解难度 */
    private final String salt = "sjajaspu-i-2jrfm;sd";
    /** 设置抢购时redis缓存的key */
    private final String key = "seckill";

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Seckill> findAll() {
        List<Seckill> seckillList = redisTemplate.boundHashOps("seckill").values();
        if (seckillList == null || seckillList.size() == 0) {
            seckillList = seckillMapper.findAll();
            for (Seckill seckill : seckillList){
                /**
                 * 1. 将抢购列表数据lambda表达式放入 redis 缓存中
                 * 注：key:被抢购表的ID
                 *     value:被抢购商品数据
                 * */
                redisTemplate.boundHashOps(key).put(seckill.getSeckillId(), seckill);
                logger.info("List<Seckill> findAll -> 从数据库中读取放入缓存中，id= " + seckill.getSeckillId());
            }
        }else{
            for (Seckill seckill : seckillList) {
                logger.info("List<Seckill> findAll -> 正在从缓存中读取, id= " + seckill.getSeckillId());
            }
        }
        return seckillList;
    }

    @Override
    public Seckill findById(Long seckillId) {
        return seckillMapper.findById(seckillId);
    }

//    @Override
//    public Seckill findById(long seckillId) {
//        return seckillMapper.findById(seckillId);
//    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = (Seckill) redisTemplate.boundHashOps(key).get(seckillId);
        if (seckill == null) {
            seckill = seckillMapper.findById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                redisTemplate.boundHashOps(key).put(seckill.getSeckillId(), seckill);
                logger.info("RedisTemplate -> 已从数据库中读取并已放入缓存中");
            }
        } else {
            logger.info("RedisTemplate -> 已在缓存中找到并已读取");
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }


    private String getMD5(Long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional  /** 默认级别 */
    public SeckillExecution executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {

        if (null == md5 || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("Panic buying");
        }
        Date nowTime = new Date();
        try {
            int insertCount = seckillOrderMapper.insertOrder(seckillId, money, userPhone);
            if (insertCount <= 0) {
                throw new RepeatKillException("Repeat to snap up.");
            } else {
                int updateCount = seckillMapper.reduceStock(seckillId, nowTime);
                if (updateCount <= 0) {
                    throw new SeckillCloseException("Panic buying closed.");
                } else {
                    SeckillOrder seckillOrder = seckillOrderMapper.findById(seckillId, userPhone);
                    Seckill seckill = (Seckill) redisTemplate.boundHashOps(key).get(seckillId);
                    seckill.setStockCount(seckill.getSeckillId() - 1);
                    redisTemplate.boundHashOps(key).put(seckillId, seckill);

                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, seckillOrder);
                }
            }
        } catch (SeckillCloseException e) {
            logger.info("Panic buying closed.");
            throw e;
        } catch (RepeatKillException e) {
            logger.info("Repeat to snap up.");
            throw e;
        } catch (Exception e) {
            logger.info("An unexpected exception has occurred.");
            logger.error(e.getMessage(), e);
            throw new SeckillException("Panic buying system internal anomalies: " + e.getMessage());
        }
    }
}
