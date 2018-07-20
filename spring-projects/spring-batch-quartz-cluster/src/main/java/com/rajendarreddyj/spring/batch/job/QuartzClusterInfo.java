package com.rajendarreddyj.spring.batch.job;

import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class QuartzClusterInfo {

    public void printUserInfo() {
        System.out.println("***      start " + new Date() + "    *************");

        System.out.println("*");
        System.out.println("*        current username is " + System.getProperty("user.name"));
        System.out.println("*        current os name is " + System.getProperty("os.name"));
        System.out.println("*");

        System.out.println("*********current user information end******************");
    }
}
