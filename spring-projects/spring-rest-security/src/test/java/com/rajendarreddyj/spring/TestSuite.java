package com.rajendarreddyj.spring;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.rajendarreddyj.spring.persistence.PersistenceTestSuite;
import com.rajendarreddyj.spring.security.SecurityTestSuite;
import com.rajendarreddyj.spring.web.LiveTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // @formatter:off
        PersistenceTestSuite.class, SecurityTestSuite.class, LiveTestSuite.class }) //
public class TestSuite {

}
