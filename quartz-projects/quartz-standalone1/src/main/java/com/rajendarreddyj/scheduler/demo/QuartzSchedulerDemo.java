package com.rajendarreddyj.scheduler.demo;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.rajendarreddyj.scheduler.job.QuartzJob;

/**
 * @author rajendarreddy
 *
 */
public class QuartzSchedulerDemo {
    public static void SimpleTriggerExample() throws SchedulerException {

        JobDetail job = JobBuilder.newJob(QuartzJob.class).withIdentity("SimpleQuartzTrigger", "Group1").build();
        // SimpleTrigger – Run every 10 seconds.
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("TriggerName", "Group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();

        Scheduler sched = new StdSchedulerFactory().getScheduler();
        sched.scheduleJob(job, trigger);
        sched.start();
    }

    public static void CronTriggerExample() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(QuartzJob.class).withIdentity("CronQuartzTrigger", "Group2").build();
        // CronTrigger – Run every 10 seconds
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("TriggerName", "Group2").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public static void main(final String[] args) throws SchedulerException {
        SimpleTriggerExample();
        CronTriggerExample();
    }
}
