package com.rajendarreddyj.spring;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityInitializer() {
        super(SecurityConfig.class, HazelCastConfig.class);
    }
}
