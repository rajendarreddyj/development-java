package com.rajendarreddyj.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogDemo {
    private static final Logger logger = LogManager.getLogger(LogDemo.class.getName());

    public static void main(final String[] args) {

        logger.debug("Hello world - debug log");
        logger.info("Hello world - info log");
        logger.warn("Hello world - warn log");
        logger.error("Hello world - error log");
    }
}
