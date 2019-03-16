package com.zjy.cld2019.quartzservice.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/15 4:00 PM
 * @Version: 1.0
 * @Des：quartz
 */
public class JobB extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(" quartz job starts");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" quartz job stops");
    }

}
