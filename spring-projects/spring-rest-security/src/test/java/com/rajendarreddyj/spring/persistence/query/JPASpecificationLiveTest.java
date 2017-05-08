package com.rajendarreddyj.spring.persistence.query;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import com.rajendarreddyj.spring.persistence.model.User;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { ConfigTest.class,
// PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class JPASpecificationLiveTest {

    // @Autowired
    // private UserRepository repository;

    private User userJohn;

    private User userTom;

    private final String URL_PREFIX = "http://localhost:8082/spring-rest-security/auth/users/spec?search=";

    @Before
    public void init() {
        this.userJohn = new User();
        this.userJohn.setFirstName("john");
        this.userJohn.setLastName("doe");
        this.userJohn.setEmail("john@doe.com");
        this.userJohn.setAge(22);
        // repository.save(userJohn);

        this.userTom = new User();
        this.userTom.setFirstName("tom");
        this.userTom.setLastName("doe");
        this.userTom.setEmail("tom@doe.com");
        this.userTom.setAge(26);
        // repository.save(userTom);
    }

    private final String EURL_PREFIX = "http://localhost:8082/spring-rest-security/auth/users/espec?search=";

    @Test
    public void givenFirstOrLastName_whenGettingListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.EURL_PREFIX + "firstName:john,'lastName:doe");
        final String result = response.body().asString();
        assertTrue(result.contains(this.userJohn.getEmail()));
        assertTrue(result.contains(this.userTom.getEmail()));
    }

    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.URL_PREFIX + "firstName:john,lastName:doe");
        final String result = response.body().asString();

        assertTrue(result.contains(this.userJohn.getEmail()));
        assertFalse(result.contains(this.userTom.getEmail()));
    }

    @Test
    public void givenFirstNameInverse_whenGettingListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.URL_PREFIX + "firstName!john");
        final String result = response.body().asString();

        assertTrue(result.contains(this.userTom.getEmail()));
        assertFalse(result.contains(this.userJohn.getEmail()));
    }

    @Test
    public void givenMinAge_whenGettingListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.URL_PREFIX + "age>25");
        final String result = response.body().asString();

        assertTrue(result.contains(this.userTom.getEmail()));
        assertFalse(result.contains(this.userJohn.getEmail()));
    }

    @Test
    public void givenFirstNamePrefix_whenGettingListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.URL_PREFIX + "firstName:jo*");
        final String result = response.body().asString();

        assertTrue(result.contains(this.userJohn.getEmail()));
        assertFalse(result.contains(this.userTom.getEmail()));
    }

    @Test
    public void givenFirstNameSuffix_whenGettingListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.URL_PREFIX + "firstName:*n");
        final String result = response.body().asString();

        assertTrue(result.contains(this.userJohn.getEmail()));
        assertFalse(result.contains(this.userTom.getEmail()));
    }

    @Test
    public void givenFirstNameSubstring_whenGettingListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.URL_PREFIX + "firstName:*oh*");
        final String result = response.body().asString();

        assertTrue(result.contains(this.userJohn.getEmail()));
        assertFalse(result.contains(this.userTom.getEmail()));
    }

    @Test
    public void givenAgeRange_whenGettingListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.URL_PREFIX + "age>20,age<25");
        final String result = response.body().asString();

        assertTrue(result.contains(this.userJohn.getEmail()));
        assertFalse(result.contains(this.userTom.getEmail()));
    }

    private final String ADV_URL_PREFIX = "http://localhost:8082/spring-rest-security/auth/users/spec/adv?search=";

    @Test
    public void givenFirstOrLastName_whenGettingAdvListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.ADV_URL_PREFIX + "firstName:john OR lastName:doe");
        final String result = response.body().asString();
        assertTrue(result.contains(this.userJohn.getEmail()));
        assertTrue(result.contains(this.userTom.getEmail()));
    }

    @Test
    public void givenFirstOrFirstNameAndAge_whenGettingAdvListOfUsers_thenCorrect() {
        final Response response = this.givenAuth().get(this.ADV_URL_PREFIX + "( firstName:john OR firstName:tom ) AND age>22");
        final String result = response.body().asString();
        assertFalse(result.contains(this.userJohn.getEmail()));
        assertTrue(result.contains(this.userTom.getEmail()));
    }

    private final RequestSpecification givenAuth() {
        return RestAssured.given().auth().preemptive().basic("user1", "user1Pass");
    }
}
