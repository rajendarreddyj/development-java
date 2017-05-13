package com.rajendarreddyj.basics.pattern.singleton;

import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class SingletonObjectDemo1 {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String args[]) {
        SingletonClass1.getSingletonObject();
        // Your Business Logic
        logger.info("Singleton object obtained");
    }
}

class SingletonClass1 {

    private static SingletonClass1 singletonObject;

    /** A private Constructor prevents any other class from instantiating. */
    private SingletonClass1() {
        // Optional Code
    }

    // Make the Access method Synchronized to prevent Thread Problems.
    public static synchronized SingletonClass1 getSingletonObject() {
        if (singletonObject == null) {
            singletonObject = new SingletonClass1();
        }
        return singletonObject;
    }

    // Override the Object clone method to prevent cloning
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}