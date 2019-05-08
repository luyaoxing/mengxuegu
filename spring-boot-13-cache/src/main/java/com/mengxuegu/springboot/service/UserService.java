package com.mengxuegu.springboot.service;

import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-10-bill
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-03-03
 * Detailed time at 23:27
 **/

@CacheConfig(cacheNames = "user")
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    //必须指定一个缓存名称，不指定报500错误
    @Cacheable(/*cacheNames = "user", */key = "#id")
    public User getUserById(Integer id){
        User user = userMapper.getUserById(id);
        return user;
    }

    //必须指定一个缓存名称，不指定报500错误
    @CachePut(/*cacheNames = "user", */key = "#user.id")
    public User updateUser(User user){
        userMapper.updateUser(user);
        return user;
    }

    /**
     *  默认情况下删除数据不会将缓存中的数据删除
     *  allEntries = true 会将缓存中的所有数据清空
     *  beforeInvocation = true 表示在方法之前执行 清空所有缓存   fusle表示在方法之后执行，这是默认的
     *  一般项目中，如果对缓存的要求很高，一般会使用缓存中间件
     * */
    @CacheEvict(/*cacheNames = "user", */key = "#id", allEntries = true/*, beforeInvocation = true*/)
    public Integer deleteUserById(Integer id){
        userMapper.deleteUserById(id);
        return id;
    }

}
