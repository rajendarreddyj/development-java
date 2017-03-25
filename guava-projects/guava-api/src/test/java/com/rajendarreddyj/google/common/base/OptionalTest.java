package com.rajendarreddyj.google.common.base;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class OptionalTest {
    OptionalTest optionalTest = null;

    @Before
    public void initialize() {
        optionalTest = new OptionalTest();
    }

    @Test(expected = NullPointerException.class)
    public void testNullOptional() {
        Integer invalidInput = null;
        Optional.of(invalidInput);
    }

    @Test
    public void testOptional() {

        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.fromNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.fromNullable(value1);
        // Optional.isPresent - checks the value is present or not
        Assert.assertFalse(a.isPresent());
        // Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = Optional.of(value2);

        Assert.assertTrue(b.isPresent());
        Assert.assertSame(optionalTest.sum(a, b), 10);
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {

        // Optional.or - returns the value if present otherwise returns
        // the default value passed.
        Integer value1 = a.or(new Integer(0));

        // Optional.get - gets the value, value should be present
        Integer value2 = b.get();

        return value1 + value2;
    }

}
