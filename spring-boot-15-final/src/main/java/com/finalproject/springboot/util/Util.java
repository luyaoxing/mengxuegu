package com.finalproject.springboot.util;

import org.springframework.util.DigestUtils;

/**
 * @program: mengxuegu
 * @description: 公共工具类
 * @author: Lunatic Princess
 * @create: 2019-05-05 20:36
 **/
public class Util {

    private final String salt = "sjajaspu-i-2jrfm;sd";

    public String getMD5(Long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
