package com.rajendarreddyj.basics.concurrent.future;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * @author rajendarreddy
 *
 */
public class SquareCalculatorTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    @Rule
    public TestName name = new TestName();
    private long start;
    private SquareCalculator squareCalculator;

    @Test
    public void givenExecutorIsSingleThreaded_whenTwoExecutionsAreTriggered_thenRunInSequence() throws InterruptedException, ExecutionException {
        this.squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());
        Future<Integer> result1 = this.squareCalculator.calculate(4);
        Future<Integer> result2 = this.squareCalculator.calculate(1000);
        while (!result1.isDone() || !result2.isDone()) {
            logger.info(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done", result2.isDone() ? "done" : "not done"));
            Thread.sleep(300);
        }
        Assert.assertEquals(16, result1.get().intValue());
        Assert.assertEquals(1000000, result2.get().intValue());
    }

    @Test(expected = TimeoutException.class)
    public void whenGetWithTimeoutLowerThanExecutionTime_thenThrowException() throws InterruptedException, ExecutionException, TimeoutException {
        this.squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());
        Future<Integer> result = this.squareCalculator.calculate(4);
        result.get(500, TimeUnit.MILLISECONDS);
    }

    @Test
    public void givenExecutorIsMultiThreaded_whenTwoExecutionsAreTriggered_thenRunInParallel() throws InterruptedException, ExecutionException {
        this.squareCalculator = new SquareCalculator(Executors.newFixedThreadPool(2));
        Future<Integer> result1 = this.squareCalculator.calculate(4);
        Future<Integer> result2 = this.squareCalculator.calculate(1000);
        while (!result1.isDone() || !result2.isDone()) {
            logger.info(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done", result2.isDone() ? "done" : "not done"));
            Thread.sleep(300);
        }
        Assert.assertEquals(16, result1.get().intValue());
        Assert.assertEquals(1000000, result2.get().intValue());
    }

    @Test(expected = CancellationException.class)
    public void whenCancelFutureAndCallGet_thenThrowException() throws InterruptedException, ExecutionException, TimeoutException {
        this.squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());
        Future<Integer> result = this.squareCalculator.calculate(4);
        boolean canceled = result.cancel(true);
        Assert.assertTrue("Future was canceled", canceled);
        Assert.assertTrue("Future was canceled", result.isCancelled());
        result.get();
    }

    @Before
    public void start() {
        this.start = System.currentTimeMillis();
    }

    @After
    public void end() {
        logger.info(String.format("Test %s took %s ms \n", this.name.getMethodName(), System.currentTimeMillis() - this.start));
    }
}
