package com.rajendarreddyj.spring.persistence.query;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyIterable.emptyIterable;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.IsNot.not;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rajendarreddyj.spring.PersistenceConfig;
import com.rajendarreddyj.spring.persistence.dao.MyUserPredicatesBuilder;
import com.rajendarreddyj.spring.persistence.dao.MyUserRepository;
import com.rajendarreddyj.spring.persistence.model.MyUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@Transactional
@Rollback
public class JPAQuerydslIntegrationTest {

    @Autowired
    private MyUserRepository repo;

    private MyUser userJohn;

    private MyUser userTom;

    @Before
    public void init() {
        this.userJohn = new MyUser();
        this.userJohn.setFirstName("john");
        this.userJohn.setLastName("doe");
        this.userJohn.setEmail("john@doe.com");
        this.userJohn.setAge(22);
        this.repo.save(this.userJohn);

        this.userTom = new MyUser();
        this.userTom.setFirstName("tom");
        this.userTom.setLastName("doe");
        this.userTom.setEmail("tom@doe.com");
        this.userTom.setAge(26);
        this.repo.save(this.userTom);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        final MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder().with("lastName", ":", "doe");

        final Iterable<MyUser> results = this.repo.findAll(builder.build());
        assertThat(results, containsInAnyOrder(this.userJohn, this.userTom));
    }

    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        final MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder().with("firstName", ":", "john").with("lastName", ":", "doe");

        final Iterable<MyUser> results = this.repo.findAll(builder.build());

        assertThat(results, contains(this.userJohn));
        assertThat(results, not(contains(this.userTom)));
    }

    @Test
    public void givenLastAndAge_whenGettingListOfUsers_thenCorrect() {
        final MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder().with("lastName", ":", "doe").with("age", ">", "25");

        final Iterable<MyUser> results = this.repo.findAll(builder.build());

        assertThat(results, contains(this.userTom));
        assertThat(results, not(contains(this.userJohn)));
    }

    @Test
    public void givenWrongFirstAndLast_whenGettingListOfUsers_thenCorrect() {
        final MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder().with("firstName", ":", "adam").with("lastName", ":", "fox");

        final Iterable<MyUser> results = this.repo.findAll(builder.build());
        assertThat(results, emptyIterable());
    }

    @Test
    public void givenPartialFirst_whenGettingListOfUsers_thenCorrect() {
        final MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder().with("firstName", ":", "jo");

        final Iterable<MyUser> results = this.repo.findAll(builder.build());

        assertThat(results, contains(this.userJohn));
        assertThat(results, not(contains(this.userTom)));
    }
}