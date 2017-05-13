package com.rajendarreddyj.basics.reflection;

import java.awt.Point;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * This class uses reflection to call various methods on strings.
 * 
 * @author rajendarreddy
 *
 */
public class ReflectionExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) throws Exception {
        // // an attempt to mutate immutable strings
        // String s = "hello";
        // String s2 = "hello";
        // Field f = s.getClass().getDeclaredField("value");
        // char[] a = (char[]) f.get(s);
        // logger.info(Arrays.toString(a));
        // get the Point class object; create two new Point()s
        Class<Point> clazz = Point.class;
        Point p = clazz.newInstance();
        // get the method Point.translate(int, int)
        Method translate = clazz.getMethod("translate", Integer.TYPE, Integer.TYPE);
        // call p.translate(4, -7);
        translate.invoke(p, 4, -7);
        // call p.getX()
        Method getX = clazz.getMethod("getX");
        double x = (Double) getX.invoke(p);
        logger.info(p.toString());
        logger.info(String.valueOf(x));
    }
}
