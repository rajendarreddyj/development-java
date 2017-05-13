package com.rajendarreddyj.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@Configuration
public class AppConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocations(new ClassPathResource("application-db.properties"));
        // propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        // propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);
        return propertySourcesPlaceholderConfigurer;
    }
}
