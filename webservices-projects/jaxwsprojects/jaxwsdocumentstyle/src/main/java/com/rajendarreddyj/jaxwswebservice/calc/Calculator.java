package com.rajendarreddyj.jaxwswebservice.calc;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface Calculator {
    @WebMethod
    int add(final int a, final int b);

    @WebMethod
    int sub(final int a, final int b);
}
