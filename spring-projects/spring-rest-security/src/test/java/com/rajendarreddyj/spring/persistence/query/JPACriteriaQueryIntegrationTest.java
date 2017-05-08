package com.rajendarreddyj.spring.persistence.query;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsNot.not;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rajendarreddyj.spring.PersistenceConfig;
import com.rajendarreddyj.spring.persistence.dao.IUserDAO;
import com.rajendarreddyj.spring.persistence.model.User;
import com.rajendarreddyj.spring.web.util.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@Transactional
@Rollback
public class JPACriteriaQueryIntegrationTest {

    @Autowired
    private IUserDAO userApi;

    private User userJohn;

    private User userTom;

    @Before
    public void init() {
        this.userJohn = new User();
        this.userJohn.setFirstName("john");
        this.userJohn.setLastName("doe");
        this.userJohn.setEmail("john@doe.com");
        this.userJohn.setAge(22);
        this.userApi.save(this.userJohn);

        this.userTom = new User();
        this.userTom.setFirstName("tom");
        this.userTom.setLastName("doe");
        this.userTom.setEmail("tom@doe.com");
        this.userTom.setAge(26);
        this.userApi.save(this.userTom);
    }

    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        final List<SearchCriteria> params = new ArrayList<>();
        params.add(new SearchCriteria("firstName", ":", "john"));
        params.add(new SearchCriteria("lastName", ":", "doe"));

        final List<User> results = this.userApi.searchUser(params);

        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        final List<SearchCriteria> params = new ArrayList<>();
        params.add(new SearchCriteria("lastName", ":", "doe"));

        final List<User> results = this.userApi.searchUser(params);
        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, isIn(results));
    }

    @Test
    public void givenLastAndAge_whenGettingListOfUsers_thenCorrect() {
        final List<SearchCriteria> params = new ArrayList<>();
        params.add(new SearchCriteria("lastName", ":", "doe"));
        params.add(new SearchCriteria("age", ">", "25"));

        final List<User> results = this.userApi.searchUser(params);

        assertThat(this.userTom, isIn(results));
        assertThat(this.userJohn, not(isIn(results)));
    }

    @Test
    public void givenWrongFirstAndLast_whenGettingListOfUsers_thenCorrect() {
        final List<SearchCriteria> params = new ArrayList<>();
        params.add(new SearchCriteria("firstName", ":", "adam"));
        params.add(new SearchCriteria("lastName", ":", "fox"));

        final List<User> results = this.userApi.searchUser(params);
        assertThat(this.userJohn, not(isIn(results)));
        assertThat(this.userTom, not(isIn(results)));
    }

    @Test
    public void givenPartialFirst_whenGettingListOfUsers_thenCorrect() {
        final List<SearchCriteria> params = new ArrayList<>();
        params.add(new SearchCriteria("firstName", ":", "jo"));

        final List<User> results = this.userApi.searchUser(params);

        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }
}