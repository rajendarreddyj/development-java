package com.rajendarreddyj.basics.others;

import java.lang.reflect.Field;

public class GetFieldsFromAnObject {

    public static void main(final String[] args) {

        Class<?> clazz = java.lang.String.class;
        Field[] fields;

        // list with all the accessible public fields of the class or interface
        fields = clazz.getFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("Found public field: " + fields[i]);
        }

        System.out.println();

        // list with all the fields declared by this class or interface
        fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("Found field: " + fields[i]);
        }

    }

}
