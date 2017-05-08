package com.rajendarreddyj.spring.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.rajendarreddyj.spring.persistence.query.JPACriteriaQueryIntegrationTest;
import com.rajendarreddyj.spring.persistence.query.JPAQuerydslIntegrationTest;
import com.rajendarreddyj.spring.persistence.query.JPASpecificationIntegrationTest;
import com.rajendarreddyj.spring.persistence.query.RsqlIntegrationTest;
import com.rajendarreddyj.spring.persistence.service.FooServicePersistenceIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // @formatter:off
        RsqlIntegrationTest.class, JPASpecificationIntegrationTest.class, FooServicePersistenceIntegrationTest.class, JPAQuerydslIntegrationTest.class,
        JPACriteriaQueryIntegrationTest.class }) //
public class PersistenceTestSuite {

}
