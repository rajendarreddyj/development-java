package com.rajendarreddyj.spring;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rajendarreddyj.spring.properties.DBProperties;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class AppConfigTest {
    private static final Logger logger = LoggerFactory.getLogger(AppConfigTest.class);

    @Test
    public void testPropLoad() {
        try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, DBProperties.class);) {
            DBProperties dbProperties = context.getBean(DBProperties.class);
            logger.info("This is dbProperties: " + dbProperties.toString());
            Assert.assertNotNull(dbProperties.getUserName());
        }
    }
}
