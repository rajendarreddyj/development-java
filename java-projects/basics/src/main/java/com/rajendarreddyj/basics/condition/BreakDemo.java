package com.rajendarreddyj.basics.condition;

public class BreakDemo {
    public static void main(final String[] args) {
        for (int i = 1; i < 100; i++) {
            if ((i % 13) == 0) {
                if (i == 65) {
                    continue;
                }
                System.out.println(i);
            }
        }
        System.out.println("After the loop");
    }
}
