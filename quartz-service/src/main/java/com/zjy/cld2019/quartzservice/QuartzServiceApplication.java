package com.zjy.cld2019.quartzservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务
 *方案1 使用boot自带的定时任务,不需要添加quartz-startar依赖
 * 方案2 使用QuartZ
 */
@SpringBootApplication
@EnableScheduling
public class QuartzServiceApplication {

    public static void main(String[] args) {

        //SpringApplication.run(QuartzServiceApplication.class, args);

        SpringApplication springApplication = new SpringApplication(QuartzServiceApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }

}
