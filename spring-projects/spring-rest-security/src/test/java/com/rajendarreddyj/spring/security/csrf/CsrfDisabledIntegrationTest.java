package com.rajendarreddyj.spring.security.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

public class CsrfDisabledIntegrationTest extends CsrfAbstractIntegrationTest {

    @Test
    public void givenNotAuth_whenAddFoo_thenUnauthorized() throws Exception {
        this.mvc.perform(post("/auth/foos").contentType(MediaType.APPLICATION_JSON).content(this.createFoo())).andExpect(status().isUnauthorized());
    }

    @Test
    public void givenAuth_whenAddFoo_thenCreated() throws Exception {
        this.mvc.perform(post("/auth/foos").contentType(MediaType.APPLICATION_JSON).content(this.createFoo()).with(this.testUser()))
                .andExpect(status().isCreated());
    }

    @Test
    public void accessMainPageWithoutAuthorization() throws Exception {
        this.mvc.perform(get("/graph.html").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void accessOtherPages() throws Exception {
        this.mvc.perform(get("/auth/transfer").contentType(MediaType.APPLICATION_JSON).param("accountNo", "1").param("amount", "100"))
                .andExpect(status().isUnauthorized()); // without authorization
        this.mvc.perform(get("/auth/transfer").contentType(MediaType.APPLICATION_JSON).param("accountNo", "1").param("amount", "100").with(this.testUser()))
                .andExpect(status().isOk()); // with authorization
    }

    @Test
    public void accessAdminPage() throws Exception {
        this.mvc.perform(get("/auth/admin/x").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized()); // without
                                                                                                                             // authorization
        this.mvc.perform(get("/auth/admin/x").contentType(MediaType.APPLICATION_JSON).with(this.testAdmin())).andExpect(status().isOk()); // with
                                                                                                                                          // authorization
    }

}
