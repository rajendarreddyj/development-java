package com.rajendarreddyj.basics.condition;

import java.util.logging.Logger;

public class CheckForPalindromeNumberWithForLoop {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        // numbers to check
        int numbers[] = new int[] { 252, 54, 99, 1233, 66, 9876 };
        // loop through the given numbers
        for (int i = 0; i < numbers.length; i++) {
            int numberToCheck = numbers[i];
            int numberInReverse = 0;
            int temp = 0;
            // a number is a palindrome if the number is equal to it's reversed
            // number
            // reverse the number
            while (numberToCheck > 0) {
                temp = numberToCheck % 10;
                numberToCheck = numberToCheck / 10;
                numberInReverse = (numberInReverse * 10) + temp;
            }
            if (numbers[i] == numberInReverse) {
                logger.info(numbers[i] + " is a palindrome");
            } else {
                logger.info(numbers[i] + " is NOT a palindrome");
            }
        }
    }
}
