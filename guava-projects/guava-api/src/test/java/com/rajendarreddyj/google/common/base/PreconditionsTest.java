package com.rajendarreddyj.google.common.base;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Preconditions;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class PreconditionsTest {
    PreconditionsTest preconditionsTest = null;

    @Before
    public void initialize() {
        preconditionsTest = new PreconditionsTest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArguments() {
        preconditionsTest.sqrt(-3.0);
    }

    @Test(expected = NullPointerException.class)
    public void testNullValues() {
        preconditionsTest.sum(null, 3);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInvalidIndex() {
        preconditionsTest.getValue(6);
    }

    public double sqrt(double input) throws IllegalArgumentException {
        Preconditions.checkArgument(input > 0.0, "Illegal Argument passed: Negative value %s.", input);
        return Math.sqrt(input);
    }

    public int sum(Integer a, Integer b) {

        a = Preconditions.checkNotNull(a, "Illegal Argument passed: First parameter is Null.");
        b = Preconditions.checkNotNull(b, "Illegal Argument passed: Second parameter is Null.");

        return a + b;
    }

    public int getValue(int input) {
        int[] data = { 1, 2, 3, 4, 5 };
        Preconditions.checkElementIndex(input, data.length, "Illegal Argument passed: Invalid index.");

        return 0;
    }
}
