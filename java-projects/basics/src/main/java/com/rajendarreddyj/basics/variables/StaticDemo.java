package com.rajendarreddyj.basics.variables;

import java.util.logging.Logger;

public class StaticDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    // static variable - the same on all instances of a class
    static int X = 10;
    // non static variable
    int Y = 5;

    @SuppressWarnings("static-access")
    public static void main(final String[] args) {
        StaticDemo instance1 = new StaticDemo();
        StaticDemo instance2 = new StaticDemo();
        logger.info("instance1.X = " + instance1.X + " instance1.Y = " + instance2.Y);
        logger.info("instance2.X = " + instance1.X + " instance2.Y = " + instance2.Y);
        instance1.X = 15;
        instance1.Y = 10;
        logger.info("After updating X value to 15 and Y value to 10 from intance1 :");
        logger.info("instance1.X = " + instance1.X + " instance1.Y = " + instance1.Y);
        logger.info("instance2.X = " + instance2.X + " instance2.Y = " + instance2.Y);
    }
}
