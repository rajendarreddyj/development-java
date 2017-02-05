package com.rajendarreddyj.basics.condition;

public class SimpleDoWhileExample {
    public static void main(final String[] args) {
        int i = 0;
        int max = 5;
        System.out.println("Counting to " + max);
        do {
            i++;
            System.out.println("i is : " + i);
        } while (i < max);
    }
}
