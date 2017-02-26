package com.rajendarreddyj.jaxwswebservice.calc.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.rajendarreddyj.jaxwswebservice.calc.Calculator;

/**
 * @author rajendarreddy
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class CalculatorImpl implements Calculator {
    @Override
    @WebMethod
    public int add(final int a, final int b) {
        return (a + b);
    }

    @Override
    @WebMethod
    public int sub(final int a, final int b) {
        return (a - b);
    }

}
