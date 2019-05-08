package com.mengxuegu.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-10-bill
 * @description: Scheduled时间表，定时任务  创建定时任务
 * *****************************************
 * 在线生成cron表达式 http://cron.qqe2.com/
 * *****************************************
 * @author: Lunatic Princess
 * @create: 2019-02-23
 * Detailed time at 21:39
 **/
@Service
public class ScheduledService {

    private static int count = 1;

    /**
     *  second()  minute()  hour()  day()  month()  week()
     *  "0 * * * * MON-FRI"
     * */
    @Scheduled(cron = "*/2 * * * * ?")
    public void dataCount(){
        System.out.println("数据统计第 " + count++ + " 次");
    }
}
