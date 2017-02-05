package com.rajendarreddyj.basics.condition;

public class FreeFlowingSwitchStatement {
    public static void main(final String[] args) {
        int i = 0;
        // break statement is not specified, thus switch statement becomes free
        // flowing
        // all cases after the matching one (including the default) will be
        // executed
        switch (i) {
        case 0:
            System.out.println("i is 0");
        case 1:
            System.out.println("i is 1");
        case 2:
            System.out.println("i is 2");
        default:
            System.out.println("Free flowing switch");
        }
    }
}
