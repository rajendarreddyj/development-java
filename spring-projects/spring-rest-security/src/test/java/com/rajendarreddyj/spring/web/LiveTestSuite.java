package com.rajendarreddyj.spring.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.rajendarreddyj.spring.persistence.query.JPASpecificationLiveTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // @formatter:off
        JPASpecificationLiveTest.class, FooDiscoverabilityLiveTest.class, FooLiveTest.class, MyUserLiveTest.class }) //
public class LiveTestSuite {

}
