package com.rajendarreddyj.scheduler;

import java.util.logging.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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
    public void execute(final JobExecutionContext arg0) throws JobExecutionException {
        logger.info("Java web application + Quartz");
    }

}
