package com.rajendarreddyj.jaxwswebservice.calc;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author rajendarreddy
 */
@WebService(name = "CalculatorImpl", targetNamespace = "http://impl.calc.jaxwswebservice.rajendarreddyj.com/")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class CalculatorService {
    @WebMethod
    public int add(final int a, final int b) {
        return (a + b);
    }

    @WebMethod
    public int sub(final int a, final int b) {
        return (a - b);
    }

}
