package com.rajendarreddyj.basics.condition;

public class BreakStatement {
    public static void main(final String[] args) {
        int array[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println("Breaking at first even number");
        for (int i = 0; i < array.length; i++) {
            if ((array[i] % 2) == 0) {
                break;
            } else {
                System.out.print(array[i] + " ");
            }
        }
    }
}
