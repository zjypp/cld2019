package com.zjy.cld2019.quartzservice.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/16 5:16 PM
 * @Version: 1.0
 * @Des：
 */
public class QuartJobA extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(" AAAAAA Job starts");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" AAAAA Job stops");
    }
}
