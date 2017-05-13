package com.rajendarreddyj.algorithms.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author rajendarreddy.jagapathi <rajendarreddyj@gmail.com>
 *
 */
public class TestQuickSort {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final Random RANDOM = new Random();
    private static final int SIZE = 10000;

    private static int[] unsorted = null;
    private static int[] sorted = null;
    private static int[] reverse = null;

    static {
        unsorted = new int[SIZE];
        int i = 0;
        while (i < unsorted.length) {
            int j = RANDOM.nextInt(unsorted.length * 10);
            unsorted[i++] = j;
        }

        sorted = new int[SIZE];
        for (i = 0; i < sorted.length; i++) {
            sorted[i] = i;
        }

        reverse = new int[SIZE];
        for (i = (reverse.length - 1); i >= 0; i--) {
            reverse[i] = (SIZE - 1) - i;
        }
    }

    @Test
    public void testQuickSorts() throws Exception {
        // Quicksort
        int[] input = { 23, 31, 1, 21, 36, 72 };
        logger.info("Before sorting : " + Arrays.toString(input));
        QuickSort.quickSort(input); // sort the integer array using quick sort algorithm
        logger.info("After sorting : " + Arrays.toString(input));
        Assert.assertTrue("Quick sort for simple input=" + print(input), check(input));
        // input with duplicates
        int[] withDuplicates = { 11, 14, 16, 12, 11, 15 };
        logger.info("Before sorting : " + Arrays.toString(withDuplicates));
        QuickSort.quickSort(withDuplicates);
        Assert.assertTrue("Quick sort for simple input with Duplicates=" + print(withDuplicates), check(withDuplicates));
        logger.info("After sorting : " + Arrays.toString(withDuplicates));
    }

    @Test
    public void quicksortUnsortedArray() {
        int[] result = QuickSort.quickSort(unsorted.clone());
        Assert.assertTrue("Quick sort unsorted array. result=" + print(result), check(result));
    }

    @Test
    public void quicksortSortedArray() {
        int[] result = QuickSort.quickSort(sorted.clone());
        Assert.assertTrue("Quick sort sorted array. result=" + print(result), check(result));
    }

    @Test
    public void quicksortReverseArray() {
        int[] result = QuickSort.quickSort(reverse.clone());
        Assert.assertTrue("Quick sort reverse array. result=" + print(result), check(result));
    }

    private static final boolean check(final int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    private static final String print(final int[] array) {
        return print(array, 0, array.length);
    }

    private static final String print(final int[] array, final int start, final int length) {
        final int[] clone = array.clone();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int e = clone[start + i];
            builder.append(e + " ");
        }
        return builder.toString();
    }
}
