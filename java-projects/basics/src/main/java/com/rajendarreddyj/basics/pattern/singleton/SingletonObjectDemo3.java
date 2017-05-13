package com.rajendarreddyj.basics.pattern.singleton;

import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class SingletonObjectDemo3 {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String args[]) {
        SingletonClass3.getSingletonObject();
        // Your Business Logic
        logger.info("Singleton object obtained");
    }
}

class SingletonClass3 {

    // Auto Initialize when instance is created
    private static SingletonClass3 singletonObject = new SingletonClass3();

    /** A private Constructor prevents any other class from instantiating. */
    private SingletonClass3() {
        // Optional Code
    }

    // Runtime initialization
    // By defualt ThreadSafe
    public static SingletonClass3 getSingletonObject() {
        return singletonObject;
    }

    // Override the Object clone method to prevent cloning
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
