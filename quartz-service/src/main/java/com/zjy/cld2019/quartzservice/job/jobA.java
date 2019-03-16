package com.zjy.cld2019.quartzservice.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/13 10:55 AM
 * @Version: 1.0
 * @Des：java，boot自带的定时任务实现
 */
@Component
public class jobA {

    @Value("port")
    String port;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    //@Scheduled(fixedDelay=5000)
    public void jobATest(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");

        logger.info("quartz " + sdf.format(d) + port) ;
        System.out.println(sdf.format(d));
    }

    //@Scheduled(cron = "0/5 * * * * *")
    public void jobATest2(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");

        logger.info("quartz test2 " + sdf.format(d));
        System.out.println(sdf.format(d));
    }

}
