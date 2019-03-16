package com.zjy.cld2019.quartzservice;


import com.zjy.cld2019.quartzservice.job.JobB;
import com.zjy.cld2019.quartzservice.job.QuartJobA;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.core.jmx.SimpleTriggerSupport.newTrigger;


/**
 * @Author: Jason Zhang
 * @Date: 2019/3/16 4:20 PM
 * @Version: 1.0
 * @Des：项目初始化执行
 * 模拟启动一个quart 任务，并且持久化到数据库
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        System.out.println("***** app startup");
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            JobDetail jobDetail1 = JobBuilder.newJob(QuartJobA.class).withIdentity("QuartJobA","group1").build();
            Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("QuartJobA","group1").startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(jobDetail1,trigger1);
            //scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
