package com.zjy.cld2019.quartzservice.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/15 4:37 PM
 * @Version: 1.0
 * @Des：
 */
public class MyCronJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("MyCronJob" + new Date());
        // indexController.testMail();
    }
}
