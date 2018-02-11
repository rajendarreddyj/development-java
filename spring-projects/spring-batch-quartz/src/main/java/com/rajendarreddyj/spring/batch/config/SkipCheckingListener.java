package com.rajendarreddyj.spring.batch.config;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class SkipCheckingListener extends StepExecutionListenerSupport {
    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        String exitCode = stepExecution.getExitStatus().getExitCode();
        if (!exitCode.equals(ExitStatus.FAILED.getExitCode()) && (stepExecution.getSkipCount() > 0)) {
            return new ExitStatus("COMPLETED WITH SKIPS");
        } else {
            return new ExitStatus("COMPLETED WITH SKIPS");
        }
    }
}
