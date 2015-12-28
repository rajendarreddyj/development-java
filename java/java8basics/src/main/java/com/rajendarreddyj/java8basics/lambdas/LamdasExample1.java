package com.rajendarreddyj.java8basics.lambdas;

import java.util.Arrays;

/**
 * @author rajendarreddy
 *
 */
public class LamdasExample1 {
	public static void main(String[] args) {
		// A lambda could be represented as a comma-separated list of
		// parameters, the –> symbol and the body.
		Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
		System.out.println("");
		// explicitly provide the type of the parameter, wrapping the definition
		// in brackets.
		Arrays.asList("a", "b", "d").forEach((String e) -> System.out.println(e));
		System.out.println("");
		// In case lambda’s body is more complex, it may be wrapped into square
		// brackets, as the usual function definition in Java.
		Arrays.asList("a", "b", "d").forEach(e -> {
			System.out.print(e);
			System.out.print(e);
		});
		System.out.println("");
		// Lambdas may reference the class members and local variables
		// (implicitly making them effectively final if they are not).
		String separator = ",";
		Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator));
		System.out.println("");
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
