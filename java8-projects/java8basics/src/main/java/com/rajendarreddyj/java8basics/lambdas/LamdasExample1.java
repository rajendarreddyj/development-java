package com.rajendarreddyj.java8basics.lambdas;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author rajendarreddy
 */
public class LamdasExample1 {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        // A lambda could be represented as a comma-separated list of
        // parameters, the –> symbol and the body.
        Arrays.asList("a", "b", "d").forEach(e -> logger.info(e));
        logger.info("");
        // explicitly provide the type of the parameter, wrapping the definition
        // in brackets.
        Arrays.asList("a", "b", "d").forEach((final String e) -> logger.info(e));
        logger.info("");
        // In case lambda’s body is more complex, it may be wrapped into square
        // brackets, as the usual function definition in Java.
        Arrays.asList("a", "b", "d").forEach(e -> {
            System.out.print(e);
            System.out.print(e);
        });
        logger.info("");
        // Lambdas may reference the class members and local variables
        // (implicitly making them effectively final if they are not).
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach((final String e) -> System.out.print(e + separator));
        logger.info("");
        // Lambdas may return a value. The type of the return value will be
        // inferred by compiler. The return statement is not required if the
        // lambda body is just a one-liner.
        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));

        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });

    }
}
