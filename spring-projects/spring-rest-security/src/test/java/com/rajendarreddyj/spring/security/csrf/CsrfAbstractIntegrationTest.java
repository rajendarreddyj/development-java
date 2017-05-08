package com.rajendarreddyj.spring.security.csrf;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajendarreddyj.spring.PersistenceConfig;
import com.rajendarreddyj.spring.WebConfig;
import com.rajendarreddyj.spring.persistence.model.Foo;
import com.rajendarreddyj.spring.security.spring.SecurityWithCsrfConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@ContextConfiguration(classes = { SecurityWithCsrfConfig.class, PersistenceConfig.class, WebConfig.class })
public abstract class CsrfAbstractIntegrationTest {

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    protected MockMvc mvc;

    //

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).addFilters(this.springSecurityFilterChain).build();
    }

    protected RequestPostProcessor testUser() {
        return user("user1").password("user1Pass").roles("USER");
    }

    protected RequestPostProcessor testAdmin() {
        return user("admin").password("adminPass").roles("USER", "ADMIN");
    }

    protected String createFoo() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new Foo(randomAlphabetic(6)));
    }
}
