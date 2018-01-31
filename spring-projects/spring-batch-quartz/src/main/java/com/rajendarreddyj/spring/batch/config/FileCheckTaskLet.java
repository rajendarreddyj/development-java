package com.rajendarreddyj.spring.batch.config;

import java.io.File;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class FileCheckTaskLet implements Tasklet, StepExecutionListener {

    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        // do something
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // no-op
    }

    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        // some logic here
        File[] files = new File("/home/rajendarreddy/test").listFiles();
        if (files.length < 1) {
            return new ExitStatus("COMPLETED");
        } else {
            return new ExitStatus("PROCESS");
        }
    }

}