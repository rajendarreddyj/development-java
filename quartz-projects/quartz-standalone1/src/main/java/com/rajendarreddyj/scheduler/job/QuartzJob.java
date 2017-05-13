package com.rajendarreddyj.scheduler.job;

import java.util.logging.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

/**
 * @author rajendarreddy
 *
 */
public class QuartzJob implements Job {
    private static final Logger logger = Logger.getAnonymousLogger();
    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        logger.info("Quartz " + "Job Key " + jobKey);
    }
}