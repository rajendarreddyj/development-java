package com.rajendarreddyj.basics.condition;

/*
 * In this example, we will store the multiples of two and store them in an array and also print them.
 */
public class TwosMultiples {
    public static void main(final String[] args) {
        int[] twosMultiples = new int[10];
        // using for loop to iterate through the numbers and once
        // limit of the array size is reached, the for loop ends.
        for (int i = 0, j = 1; i < twosMultiples.length;) {
            if ((j % 2) == 0) {
                twosMultiples[i] = j;
                i++;// Incrementing to have next element in array to get next multiple
                j++;//
            } else {
                j++;// Incrementing to try with next number
            }
        }
        // For loop to iterate through the array using for each
        for (int i : twosMultiples) {
            System.out.println(i);
        }
    }
}
