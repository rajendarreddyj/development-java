package com.rajendarreddyj.spring.batch.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.rajendarreddyj")
public class Main {
    public static void main(final String[] args) {
        SpringApplication.run(Main.class, args);
    }

}