package com.mengxuegu.springboot.service;

import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import com.mengxuegu.springboot.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-10-bill
 * @description: 不使用spring里面的注解，这次使用redis的API来操作缓存.redis的工具包已经打包成工具类了。utils包下
 * @author: Lunatic Princess
 * @create: 2019-03-17
 * Detailed time at 15:32
 **/

@Service
public class ProviderService {

    @Autowired
    RedisClient redisClient;

    @Autowired
    ProviderMapper providerMapper;

    public Provider getProviderByPid(Integer pid) {
        //1. 先查询以下缓存中是否有数据
        Object obj = redisClient.get(pid);
        if (obj != null) {
            return (Provider) obj;
        }
        //2. 如果缓存中没有，则查询数据库，然后添加到缓存中
        Provider provider = providerMapper.getProviderByPid(pid);
        redisClient.set(pid, provider);
        return provider;
    }

    public Integer updateProvider(Provider provider) {
        int size = providerMapper.updateProvider(provider);
        if (size > 0) {
            redisClient.set(provider.getPid(), provider);
        }
        return size;
    }

    public Integer addProvider(Provider provider) {
        int size = providerMapper.addProvider(provider);
        if (size > 0) {
            redisClient.set(provider.getPid(), provider);
        }
        return size;
    }

    public Integer deleteProviderByPid(Integer pid) {
        int size = providerMapper.deleteProviderByPid(pid);
        if (size > 0) {
            // 清除 key=pid 的缓存
            redisClient.del(pid);
        }
        return size;
    }
}
