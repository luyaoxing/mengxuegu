package com.mengxuegu.springboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @program: spring-boot-10-bill
 * @description: 让redis不默认使用jdk序列化器，我们自己配置序列化器
 * @author: Lunatic Princess
 * @create: 2019-03-11
 * Detailed time at 23:06
 **/

@Configuration
public class RedisConfiguration {


    @Bean
    public RedisTemplate<Object, Object> jackRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer(Object.class));
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
