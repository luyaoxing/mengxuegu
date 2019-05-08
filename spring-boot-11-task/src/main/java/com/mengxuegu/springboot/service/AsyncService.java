package com.mengxuegu.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-10-bill
 * @description: 针对任务异步处理的Service层   异步处理批量操作
 * @author: Lunatic Princess
 * @create: 2019-02-23
 * Detailed time at 21:25
 **/
@Service
public class AsyncService {

    @Async
    public void batchAdd(){
        try {
            //模拟新增数据
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("批量新增数据完成");
    }
}
