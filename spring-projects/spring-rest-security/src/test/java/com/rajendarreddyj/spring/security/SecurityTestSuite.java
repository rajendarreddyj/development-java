package com.rajendarreddyj.spring.security;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.rajendarreddyj.spring.security.csrf.CsrfDisabledIntegrationTest;
import com.rajendarreddyj.spring.security.csrf.CsrfEnabledIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // @formatter:off
        CsrfEnabledIntegrationTest.class, CsrfDisabledIntegrationTest.class }) //
public class SecurityTestSuite {

}
