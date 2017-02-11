package com.rajendarreddyj.basics.operators;

/**
 * Using bitwise operators
 * 
 * @rajendarreddy
 */
public class BitwiseOperators {
    public static void main(final String[] args) {
        byte x, y;
        x = 10;
        y = 11;
        System.out.println("~x= " + (~x));
        System.out.println("x&y= " + (x & y));
        System.out.println("x|y= " + (x | y));
        System.out.println("x^y= " + (x ^ y));
        System.out.println("x<<y= " + (x << 2));
        System.out.println("x>>2= " + (x >> 2));
        System.out.println("x>>>2= " + (x >>> 2));
    }

}
