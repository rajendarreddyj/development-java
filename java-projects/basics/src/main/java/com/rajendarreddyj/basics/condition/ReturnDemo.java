package com.rajendarreddyj.basics.condition;

public class ReturnDemo {
    public void multiples(final int x) {
        for (int i = 1; i <= 10; i++) {
            if (i == 9) {
                return;
            }
            System.out.println(x * i);
        }
    }

    public static void main(final String[] args) {
        System.out.println("Before calling the Multiples");
        ReturnDemo returnDemo = new ReturnDemo();
        returnDemo.multiples(5);
        System.out.println("Multiples Method finished");
    }
}
