package com.rajendarreddyj.spring.web;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.rajendarreddyj.spring.ConfigTest;
import com.rajendarreddyj.spring.common.web.AbstractBasicLiveTest;
import com.rajendarreddyj.spring.persistence.model.Foo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class }, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class FooLiveTest extends AbstractBasicLiveTest<Foo> {

    public FooLiveTest() {
        super(Foo.class);
    }

    // API

    @Override
    public final void create() {
        this.create(new Foo(randomAlphabetic(6)));
    }

    @Override
    public final String createAsUri() {
        return this.createAsUri(new Foo(randomAlphabetic(6)));
    }

}
