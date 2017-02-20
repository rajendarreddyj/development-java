package com.rajendarreddyj.algorithms.sort;

/**
 * Quicksort is a sorting algorithm which, on average, makes O(n*log n) comparisons to sort n items. In the worst case,
 * it makes O(n^2) comparisons, though this behavior is rare. Quicksort is often faster in practice than other
 * algorithms.
 * 
 * Average case = O(n*log n), Worst case = O(n^2), Best case = O(n) [three-way partition and equal keys]
 * 
 * http://en.wikipedia.org/wiki/Quick_sort
 * 
 * @author rajendarreddy.jagapathi <rajendarreddyj@gmail.com>
 *
 */
public class QuickSort {
    /**
     * public method exposed to client, sorts given array using QuickSort Algorithm in Java
     * 
     * @param array
     */
    public static int[] quickSort(final int[] array) {
        if (array == null) {
            throw new NullPointerException("array cannot be null");
        }
        if (array.length == 0) {
            return array;
        }
        // pre: array is full, all elements are non-null integers
        // quicksort all the elements in the array
        recursiveQuickSort(array, 0, array.length - 1);
        // post: the array is sorted in ascending order
        return array;
    }

    /**
     * Recursive quicksort logic
     *
     * @param array
     *            An array of integers that is to be sorted.
     * @param lowerBound
     *            The lower index of the region of the array that is to be sorted.
     * @param upperBound
     *            The upper index of the region of the array that is to be sorted.
     */
    private static void recursiveQuickSort(final int[] array, final int lowerIndex, final int upperIndex) {

        // check that there are at least two elements to sort
        if ((upperIndex - lowerIndex) >= 1) {
            int idx = partition(array, lowerIndex, upperIndex);

            // Recursively call quicksort with left part of the partitioned array
            if (lowerIndex < (idx - 1)) {
                recursiveQuickSort(array, lowerIndex, idx - 1);
            }

            // Recursively call quick sort with right part of the partitioned array
            if (upperIndex > idx) {
                recursiveQuickSort(array, idx, upperIndex);
            }
        } else {
            // if there is only one element in the partition, do not do any sorting
            return;
        }
        // the array is sorted, so exit
    }

    /**
     * Divides array from pivot, left side contains elements less than Pivot while right side contains elements greater
     * than pivot.
     *
     * @param array
     *            array to partitioned
     * @param lowerIndex
     *            lower bound of the array
     * @param upperIndex
     *            upper bound of the array
     * @return the partition index
     */
    private static int partition(final int[] array, final int lowerIndex, final int upperIndex) {
        int left = lowerIndex;
        int right = upperIndex;
        // set a pivot, simply taking the middle value of the array
        int pivot = array[(left + right) / 2];

        // while the scan indices from left and right have not met,
        while (left <= right) {
            // searching number which is greater than pivot, bottom up
            // check if array value at left is less than our pivot
            while (array[left] < pivot) {
                // if it is less-than pivot, the value array[left] is in correct place
                // so we can increment i to go to next
                left++;
            }
            // searching number which is less than pivot, top down
            // now do exactly same thing for right Index, however, right Index starts at array.length, so decrement
            while (array[right] > pivot) {
                right--;
            }
            // if the left seekindex is still smaller than the right index, swap the corresponding elements
            if (left <= right) {
                swap(array, left, right);
                // increment left index and decrement right index
                left++;
                right--;
            }
        }
        return left;
    }

    /**
     * @param array
     * @param index1
     * @param index2
     */
    private static void swap(final int[] array, final int index1, final int index2) {
        if (index1 != index2) {
            // pre: array is full and index1, index2 < array.length
            // store the first value in a temp
            int temp = array[index1];
            // copy the value of the second into the first
            array[index1] = array[index2];
            // copy the value of the temp into the second
            array[index2] = temp;
            // post: the values at indices 1 and 2 have been swapped
        }
    }
}
