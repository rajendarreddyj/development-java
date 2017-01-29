package com.rajendarreddyj.jaxwswebservice.calc;

import javax.jws.WebService;

/**
 * @author rajendarreddy
 */
@WebService
public class Calculator {
    public int add(final int a, final int b) {
        return (a + b);
    }

    public int sub(final int a, final int b) {
        return (a - b);
    }

}
