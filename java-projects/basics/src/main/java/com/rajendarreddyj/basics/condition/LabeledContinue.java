package com.rajendarreddyj.basics.condition;

public class LabeledContinue {
    public static void main(final String[] args) {
        first: for (int i = 2; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (i == 7) {
                    // continue first;
                    break first;
                }
                System.out.println(i + "*" + j + "=" + (i * j));
            }
        }
    }
}
