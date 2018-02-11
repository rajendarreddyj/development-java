package com.rajendarreddyj.spring.batch.config;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class MyDecider implements JobExecutionDecider {
    public static final String COMPLETED = "COMPLETED";
    public static final String FAILED = "FAILED";

    @Override
    public FlowExecutionStatus decide(final JobExecution jobExecution, final StepExecution stepExecution) {

        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            return FlowExecutionStatus.COMPLETED;
        } else {
            return FlowExecutionStatus.COMPLETED;
        }
    }

}
