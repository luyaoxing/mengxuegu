package com.finalproject.springboot.mapper;

import com.finalproject.springboot.entity.SeckillOrder;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @program: mengxuegu
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-05-05 20:03
 **/
public interface SeckillOrderMapper {


    /**
     * 插入购买订单明细
     *
     * @param seckillId 秒杀到的商品ID
     * @param money     秒杀的金额
     * @param userPhone 秒杀的用户
     * @return 返回该SQL更新的记录数，如果>=1则更新成功
     */
    int insertOrder(@Param("seckillId") long seckillId, @Param("money") BigDecimal money, @Param("userPhone") long userPhone);

    /**
     * 根据秒杀商品ID查询订单明细数据
     * 并得到对应秒杀商品的数据
     * 因为我们再SeckillOrder中已经定义了一个Seckill的属性
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    SeckillOrder findById(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
