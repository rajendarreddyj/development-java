package com.rajendarreddyj.jaxwswebserviceclient.calc;

import com.rajendarreddyj.jaxwswebservice.calc.Calculator;
import com.rajendarreddyj.jaxwswebservice.calc.CalculatorService;

public class CalculatorClient {

	public static void main(String[] args) {
		int a = 10;
        int b = 12;
        CalculatorService calcService = new CalculatorService();
        Calculator calc = calcService.getCalculatorPort();
        System.out.println(a + " + " + b + " = " + calc.add(a, b));
        System.out.println(a + " - " + b + " = " + calc.sub(a, b));
	}

}
