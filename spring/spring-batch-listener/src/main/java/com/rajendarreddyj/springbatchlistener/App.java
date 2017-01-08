package com.rajendarreddyj.springbatchlistener;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private static ApplicationContext ctx;

    public static void main(final String[] args) {
        String[] str = { "META-INF/spring/context-config.xml", "META-INF/spring/job-config.xml" };
        ctx = new ClassPathXmlApplicationContext(str);
        Job job = (Job) ctx.getBean("dbToXml");
        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
        try {
            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Job Execution Status: " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
