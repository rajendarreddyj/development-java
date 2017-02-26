package com.rajendarreddyj.jaxwswebservice.calc;

import org.junit.Assert;
import org.junit.Test;

import com.rajendarreddyj.jaxwswebservice.calc.impl.CalculatorImpl;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class CalculatorTest {

    @Test
    public void TestAdd() {
        Calculator calculator = new CalculatorImpl();
        Assert.assertNotSame(2, calculator.add(1, 2));
        Assert.assertSame(3, calculator.add(1, 2));
    }

    @Test
    public void TestSub() {
        Calculator calculator = new CalculatorImpl();
        Assert.assertNotSame(2, calculator.sub(1, 2));
        Assert.assertSame(-1, calculator.sub(1, 2));
    }
}
