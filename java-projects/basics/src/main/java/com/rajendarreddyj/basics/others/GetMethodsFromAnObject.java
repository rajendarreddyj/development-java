package com.rajendarreddyj.basics.others;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class GetMethodsFromAnObject {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        Class<?> clazz = java.lang.ThreadLocal.class;
        Method[] methods;
        // list with all public member methods of the class or interface
        methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            logger.info("Found public method: " + methods[i]);
        }
        // list with all member methods of the class or interface
        methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            logger.info("Found method: " + methods[i]);
        }
    }
}
