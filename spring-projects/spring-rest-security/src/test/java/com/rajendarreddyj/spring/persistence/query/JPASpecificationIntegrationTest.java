package com.rajendarreddyj.spring.persistence.query;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsNot.not;

import java.util.List;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rajendarreddyj.spring.PersistenceConfig;
import com.rajendarreddyj.spring.persistence.dao.GenericSpecificationsBuilder;
import com.rajendarreddyj.spring.persistence.dao.UserRepository;
import com.rajendarreddyj.spring.persistence.dao.UserSpecification;
import com.rajendarreddyj.spring.persistence.dao.UserSpecificationsBuilder;
import com.rajendarreddyj.spring.persistence.model.User;
import com.rajendarreddyj.spring.web.util.CriteriaParser;
import com.rajendarreddyj.spring.web.util.SearchOperation;
import com.rajendarreddyj.spring.web.util.SpecSearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@Transactional
@Rollback
public class JPASpecificationIntegrationTest {

    @Autowired
    private UserRepository repository;

    private User userJohn;

    private User userTom;

    private User userPercy;

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

        this.userPercy = new User();
        this.userPercy.setFirstName("percy");
        this.userPercy.setLastName("blackney");
        this.userPercy.setEmail("percy@blackney.com");
        this.userPercy.setAge(30);
        this.repository.save(this.userPercy);
    }

    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.EQUALITY, "john"));
        final UserSpecification spec1 = new UserSpecification(new SpecSearchCriteria("lastName", SearchOperation.EQUALITY, "doe"));
        final List<User> results = this.repository.findAll(Specifications.where(spec).and(spec1));

        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }

    @Test
    public void givenFirstOrLastName_whenGettingListOfUsers_thenCorrect() {
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();

        SpecSearchCriteria spec = new SpecSearchCriteria("firstName", SearchOperation.EQUALITY, "john");
        SpecSearchCriteria spec1 = new SpecSearchCriteria("'", "lastName", SearchOperation.EQUALITY, "doe");

        List<User> results = this.repository.findAll(builder.with(spec).with(spec1).build());

        assertThat(results, hasSize(2));
        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, isIn(results));
    }

    @Test
    public void givenFirstOrLastNameAndAgeGenericBuilder_whenGettingListOfUsers_thenCorrect() {
        GenericSpecificationsBuilder<User> builder = new GenericSpecificationsBuilder<>();
        Function<SpecSearchCriteria, Specification<User>> converter = UserSpecification::new;

        CriteriaParser parser = new CriteriaParser();
        List<User> results = this.repository.findAll(builder.build(parser.parse("( lastName:doe OR firstName:john ) AND age:22"), converter));

        assertThat(results, hasSize(1));
        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }

    @Test
    public void givenFirstOrLastNameGenericBuilder_whenGettingListOfUsers_thenCorrect() {
        GenericSpecificationsBuilder<User> builder = new GenericSpecificationsBuilder<>();
        Function<SpecSearchCriteria, Specification<User>> converter = UserSpecification::new;

        builder.with("firstName", ":", "john", null, null);
        builder.with("'", "lastName", ":", "doe", null, null);

        List<User> results = this.repository.findAll(builder.build(converter));

        assertThat(results, hasSize(2));
        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, isIn(results));
    }

    @Test
    public void givenFirstNameInverse_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.NEGATION, "john"));
        final List<User> results = this.repository.findAll(Specifications.where(spec));

        assertThat(this.userTom, isIn(results));
        assertThat(this.userJohn, not(isIn(results)));
    }

    @Test
    public void givenMinAge_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("age", SearchOperation.GREATER_THAN, "25"));
        final List<User> results = this.repository.findAll(Specifications.where(spec));
        assertThat(this.userTom, isIn(results));
        assertThat(this.userJohn, not(isIn(results)));
    }

    @Test
    public void givenFirstNamePrefix_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.STARTS_WITH, "jo"));
        final List<User> results = this.repository.findAll(spec);
        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }

    @Test
    public void givenFirstNameSuffix_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.ENDS_WITH, "n"));
        final List<User> results = this.repository.findAll(spec);
        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }

    @Test
    public void givenFirstNameSubstring_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.CONTAINS, "oh"));
        final List<User> results = this.repository.findAll(spec);

        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }

    @Test
    public void givenAgeRange_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("age", SearchOperation.GREATER_THAN, "20"));
        final UserSpecification spec1 = new UserSpecification(new SpecSearchCriteria("age", SearchOperation.LESS_THAN, "25"));
        final List<User> results = this.repository.findAll(Specifications.where(spec).and(spec1));

        assertThat(this.userJohn, isIn(results));
        assertThat(this.userTom, not(isIn(results)));
    }
}
