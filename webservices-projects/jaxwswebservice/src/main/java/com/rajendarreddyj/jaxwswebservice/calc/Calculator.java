package com.rajendarreddyj.jaxwswebservice.calc;

import javax.jws.WebService;
/**
 * @author rajendarreddy
 *
 */
@WebService
public class Calculator {
    public int add(int a, int b) {
        return (a + b);
    }
     
    public int sub(int a, int b) {
        return (a - b);
    }
     
}
