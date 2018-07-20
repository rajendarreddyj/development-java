package com.rajendarreddyj.spring.batch.cron;

import java.text.ParseException;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {

    @Override
    public void afterPropertiesSet() throws ParseException {
        super.afterPropertiesSet();
        getJobDataMap().remove(getObject().getJobKey().getName());
    }
}
