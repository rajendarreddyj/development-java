package com.rajendarreddyj.spring.batch.quartz.config;

import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.rajendarreddyj.spring.batch.config.AutowiringSpringBeanJobFactory;
import com.rajendarreddyj.spring.batch.job.DisplayCurrentTimeJob;

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
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    ThreadPoolTaskExecutor executer() {
        ThreadPoolTaskExecutor executer = new ThreadPoolTaskExecutor();
        executer.setQueueCapacity(100);
        executer.setCorePoolSize(15);
        executer.setMaxPoolSize(25);
        return executer;
    }

  /*  @Bean
    DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }*/

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
    public JobDetailFactoryBean displayCurrentTimeJob() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(DisplayCurrentTimeJob.class);
        factory.setDurability(true);
        factory.setRequestsRecovery(false);
        return factory;
    }

    // Job is scheduled after every 1 minute
    @Bean
    public CronTriggerFactoryBean displayCurrentTimeScheduler() {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(this.displayCurrentTimeJob().getObject());
        stFactory.setStartDelay(3000);
        stFactory.setName("mytrigger");
        stFactory.setGroup("mygroup");
        stFactory.setCronExpression("* * * * * ? *");
        stFactory.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
        return stFactory;
    }

    @Bean
    public SchedulerFactoryBean quartzScheduler(JobFactory jobFactory) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setSchedulerName("baseScheduler");
        scheduler.setDataSource(dataSource);
        //scheduler.setTransactionManager(transactionManager());
        scheduler.setOverwriteExistingJobs(true);
        scheduler.setTaskExecutor(executer());
        scheduler.setTriggers(this.displayCurrentTimeScheduler().getObject());
        scheduler.setJobDetails(this.displayCurrentTimeJob().getObject());
        scheduler.setJobFactory(jobFactory);
        return scheduler;
    }
}
