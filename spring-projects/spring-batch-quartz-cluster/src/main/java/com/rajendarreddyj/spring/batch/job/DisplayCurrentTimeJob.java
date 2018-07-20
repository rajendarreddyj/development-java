package com.rajendarreddyj.spring.batch.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DisplayCurrentTimeJob extends QuartzJobBean {

    @Autowired
    private QuartzClusterInfo quartzClusterInfo;

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        quartzClusterInfo.printUserInfo();
    }
}
