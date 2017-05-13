package com.rajendarreddyj.basics.others;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class GetFieldsFromAnObject {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        Class<?> clazz = java.lang.String.class;
        Field[] fields;
        // list with all the accessible public fields of the class or interface
        fields = clazz.getFields();
        for (int i = 0; i < fields.length; i++) {
            logger.info("Found public field: " + fields[i]);
        }
        // list with all the fields declared by this class or interface
        fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            logger.info("Found field: " + fields[i]);
        }
    }
}
