package com.mengxuegu.springboot;

import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot13CacheApplicationTests {

    //操作的是 复杂类型  User
    @Autowired
    RedisTemplate redisTemplate;

    //针对的都是 操作字符串
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //自定义的json序列化器
    @Autowired
    RedisTemplate jackRedisTemplate;

    @Autowired
    UserService userService;


    /**
     * 五大数据类型：
     * stringRedisTemplate.opsForValue();//操作字符串
     *         stringRedisTemplate.opsForList();//操作List
     *         stringRedisTemplate.opsForSet();//操作Set
     *         stringRedisTemplate.opsForZSet();//操作ZSet
     *         stringRedisTemplate.opsForHash();//操作Hash
     * */
    @Test
    public void contextLoads() {

        stringRedisTemplate.opsForValue().set("name", "mengxuegu");
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);

//        stringRedisTemplate.opsForList().leftPush("myList", "a");
//        stringRedisTemplate.opsForList().leftPushAll("myList", "a", "b");
        List<String> myList = stringRedisTemplate.opsForList().range("myList", 0, -1);
        System.out.println(myList);  //[b, a, a]
    }

    @Test
    public void testRedis(){
        User user = userService.getUserById(4);
        System.out.println(user+"6666666666666666666666666666");
        /**
         * 保存的数据对象必须序列化 implements Serializable
         * 因为redisTemplate默认采用的是jdk序列化器
         */
//        redisTemplate.opsForValue().set("user", user);
        Object user1 = redisTemplate.opsForValue().get("user");

        /** 这里不使用jdk默认的序列化器，直接使用jsckJson序列化器，这样子在redis里的数据就是json */
        jackRedisTemplate.opsForValue().set("user2", user);
        Object user2 = jackRedisTemplate.opsForValue().get("user2");
        System.out.println(user1);
        System.out.println(user2 + "66666666666666666666666666");
    }

}
