package com.rajendarreddyj.spring.batch.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@EnableTask
//@EnableBinding(Sink.class)
//@EnableTaskLauncher
@SpringBootApplication
@EnableBatchProcessing
@EnableAutoConfiguration
@ComponentScan("com.rajendarreddyj")
public class Main {
    public CommandLineRunner commandLineRunner() {
        return strings -> System.out.println("Executed at :" + new SimpleDateFormat().format(new Date()));
    }

    public static void main(final String[] args) {
        SpringApplication.run(Main.class, args);
    }

}