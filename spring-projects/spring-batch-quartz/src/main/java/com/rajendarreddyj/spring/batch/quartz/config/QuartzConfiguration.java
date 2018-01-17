package com.rajendarreddyj.spring.batch.quartz.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.rajendarreddyj.spring.batch.quartz.JobLauncherDetails;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@Configuration
public class QuartzConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @PostConstruct
    public void init() {
        this.log.debug("QuartzConfiguration initialized.");
    }

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DataSource dataSource;

    @Bean
    SimpleJobLauncher jobLauncher() {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(this.jobRepository);
        launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return launcher;
    }

    @Bean
    JobRegistry jobRegistry() {
        return new MapJobRegistry();
    }

    @Bean
    JobExplorerFactoryBean jobExplorer() {
        final JobExplorerFactoryBean jobExplorer = new JobExplorerFactoryBean();
        jobExplorer.setDataSource(this.dataSource);
        return jobExplorer;
    }

    @Bean
    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
        final JobRegistryBeanPostProcessor processor = new JobRegistryBeanPostProcessor();
        processor.setJobRegistry(this.jobRegistry());
        return processor;
    }

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(JobLauncherDetails.class);
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "importUserJob");
        jobDataMap.put("jobLocator", this.jobRegistry());
        jobDataMap.put("jobLauncher", this.jobLauncher());
        factory.setJobDataMap(jobDataMap);
        factory.setGroup("mygroup");
        factory.setName("importUserJob");
        return factory;
    }

    // Job is scheduled after every 1 minute
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(this.jobDetailFactoryBean().getObject());
        stFactory.setStartDelay(3000);
        stFactory.setName("mytrigger");
        stFactory.setGroup("mygroup");
        stFactory.setCronExpression("* * * * * ? *");
        return stFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(this.cronTriggerFactoryBean().getObject());
        return scheduler;
    }
}
