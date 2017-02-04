package com.rajendarreddyj.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author rajendarreddy
 *
 */
public class QuartzJob implements Job {

    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(final JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("Java web application + Quartz");
    }

}
