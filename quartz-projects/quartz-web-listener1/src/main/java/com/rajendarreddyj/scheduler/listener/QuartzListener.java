package com.rajendarreddyj.scheduler.listener;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.rajendarreddyj.scheduler.QuartzJob;

/**
 * @author rajendarreddy
 *
 */
public class QuartzListener implements ServletContextListener {
    private static final Logger logger = Logger.getAnonymousLogger();
    Scheduler scheduler = null;

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(final ServletContextEvent arg0) {
        logger.info("Context Destroyed");
        try {
            this.scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(final ServletContextEvent arg0) {
        logger.info("Context Initialized");

        try {
            // Setup the Job class and the Job group
            JobDetail job = JobBuilder.newJob(QuartzJob.class).withIdentity("CronQuartzJob", "Group").build();

            // Create a Trigger that fires every 5 minutes.
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("TriggerName", "Group").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                    .build();

            // Setup the Job and Trigger with Scheduler & schedule jobs
            this.scheduler = new StdSchedulerFactory().getScheduler();
            this.scheduler.start();
            this.scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
