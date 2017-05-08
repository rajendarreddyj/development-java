package com.rajendarreddyj.spring.persistence.query;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsNot.not;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rajendarreddyj.spring.PersistenceConfig;
import com.rajendarreddyj.spring.persistence.dao.UserRepository;
import com.rajendarreddyj.spring.persistence.dao.rsql.CustomRsqlVisitor;
import com.rajendarreddyj.spring.persistence.model.User;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@Transactional
@Rollback
public class RsqlIntegrationTest {

    @Autowired
    private UserRepository repository;

    private User userJohn;

    private User userTom;

    @Before
    public void init() {
        this.userJohn = new User();
        this.userJohn.setFirstName("john");
        this.userJohn.setLastName("doe");
        this.userJohn.setEmail("john@doe.com");
        this.userJohn.setAge(22);
        this.repository.save(this.userJohn);

        this.userTom = new User();
        this.userTom.setFirstName("tom");
        this.userTom.setLastName("doe");
        this.userTom.setEmail("tom@doe.com");
        this.userTom.setAge(26);
        this.repository.save(this.userTom);
    }

    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        final Node rootNode = new RSQLParser().parse("firstName==john;lastName==doe");
        final Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<User>());
        final List<User> results = this.repository.findAll(spec);

        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }

    @Test
    public void givenFirstNameInverse_whenGettingListOfUsers_thenCorrect() {
        final Node rootNode = new RSQLParser().parse("firstName!=john");
        final Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<User>());
        final List<User> results = this.repository.findAll(spec);

        assertThat(this.userTom, isIn(results));
        assertThat(this.userJohn, not(isIn(results)));
    }

    @Test
    public void givenMinAge_whenGettingListOfUsers_thenCorrect() {
        final Node rootNode = new RSQLParser().parse("age>25");
        final Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<User>());
        final List<User> results = this.repository.findAll(spec);

        assertThat(this.userTom, isIn(results));
        assertThat(this.userJohn, not(isIn(results)));
    }

    @Test
    public void givenFirstNamePrefix_whenGettingListOfUsers_thenCorrect() {
        final Node rootNode = new RSQLParser().parse("firstName==jo*");
        final Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<User>());
        final List<User> results = this.repository.findAll(spec);

        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }

    @Test
    public void givenListOfFirstName_whenGettingListOfUsers_thenCorrect() {
        final Node rootNode = new RSQLParser().parse("firstName=in=(john,jack)");
        final Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<User>());
        final List<User> results = this.repository.findAll(spec);

        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }
}
