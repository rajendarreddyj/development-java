package com.rajendarreddyj.spring.persistence.service;

import com.rajendarreddyj.spring.persistence.IOperations;
import com.rajendarreddyj.spring.persistence.model.Foo;

public interface IFooService extends IOperations<Foo> {

    Foo retrieveByName(String name);

}
