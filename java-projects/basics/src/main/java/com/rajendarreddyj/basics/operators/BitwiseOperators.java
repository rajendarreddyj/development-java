package com.rajendarreddyj.basics.operators;

import java.util.logging.Logger;

/**
 * Using bitwise operators
 * 
 * @rajendarreddy
 */
public class BitwiseOperators {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        byte x, y;
        x = 10;
        y = 11;
        logger.info("~x= " + (~x));
        logger.info("x&y= " + (x & y));
        logger.info("x|y= " + (x | y));
        logger.info("x^y= " + (x ^ y));
        logger.info("x<<y= " + (x << 2));
        logger.info("x>>2= " + (x >> 2));
        logger.info("x>>>2= " + (x >>> 2));
    }

}
