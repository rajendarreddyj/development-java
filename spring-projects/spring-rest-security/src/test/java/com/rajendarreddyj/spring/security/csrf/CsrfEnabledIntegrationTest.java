package com.rajendarreddyj.spring.security.csrf;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

public class CsrfEnabledIntegrationTest extends CsrfAbstractIntegrationTest {

    @Test
    public void givenNoCsrf_whenAddFoo_thenForbidden() throws Exception {
        this.mvc.perform(post("/auth/foos").contentType(MediaType.APPLICATION_JSON).content(this.createFoo()).with(this.testUser()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void givenCsrf_whenAddFoo_thenCreated() throws Exception {
        this.mvc.perform(post("/auth/foos").contentType(MediaType.APPLICATION_JSON).content(this.createFoo()).with(this.testUser()).with(csrf()))
                .andExpect(status().isCreated());
    }

}
