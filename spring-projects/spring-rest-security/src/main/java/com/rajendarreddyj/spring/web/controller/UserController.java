package com.rajendarreddyj.spring.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.rajendarreddyj.spring.persistence.dao.GenericSpecificationsBuilder;
import com.rajendarreddyj.spring.persistence.dao.IUserDAO;
import com.rajendarreddyj.spring.persistence.dao.MyUserPredicatesBuilder;
import com.rajendarreddyj.spring.persistence.dao.MyUserRepository;
import com.rajendarreddyj.spring.persistence.dao.UserRepository;
import com.rajendarreddyj.spring.persistence.dao.UserSpecification;
import com.rajendarreddyj.spring.persistence.dao.UserSpecificationsBuilder;
import com.rajendarreddyj.spring.persistence.dao.rsql.CustomRsqlVisitor;
import com.rajendarreddyj.spring.persistence.model.MyUser;
import com.rajendarreddyj.spring.persistence.model.User;
import com.rajendarreddyj.spring.web.util.CriteriaParser;
import com.rajendarreddyj.spring.web.util.SearchCriteria;
import com.rajendarreddyj.spring.web.util.SearchOperation;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

// @EnableSpringDataWebSupport
@Controller
@RequestMapping(value = "/auth/")
public class UserController {

    @Autowired
    private IUserDAO service;

    @Autowired
    private UserRepository dao;

    @Autowired
    private MyUserRepository myUserRepository;

    public UserController() {
        super();
    }

    // API - READ

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseBody
    public List<User> findAll(@RequestParam(value = "search", required = false) final String search) {
        List<SearchCriteria> params = new ArrayList<>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return this.service.searchUser(params);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/spec")
    @ResponseBody
    public List<User> findAllBySpecification(@RequestParam(value = "search") final String search) {
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<User> spec = builder.build();
        return this.dao.findAll(spec);
    }

    @GetMapping(value = "/users/espec")
    @ResponseBody
    public List<User> findAllByOrPredicate(@RequestParam(value = "search") final String search) {
        Specification<User> spec = this.resolveSpecification(search);
        return this.dao.findAll(spec);
    }

    @GetMapping(value = "/users/spec/adv")
    @ResponseBody
    public List<User> findAllByAdvPredicate(@RequestParam(value = "search") final String search) {
        Specification<User> spec = this.resolveSpecificationFromInfixExpr(search);
        return this.dao.findAll(spec);
    }

    protected Specification<User> resolveSpecificationFromInfixExpr(final String searchParameters) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<User> specBuilder = new GenericSpecificationsBuilder<>();
        return specBuilder.build(parser.parse(searchParameters), UserSpecification::new);
    }

    protected Specification<User> resolveSpecification(final String searchParameters) {

        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(searchParameters + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
        }
        return builder.build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/myusers")
    @ResponseBody
    public Iterable<MyUser> findAllByQuerydsl(@RequestParam(value = "search") final String search) {
        MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        BooleanExpression exp = builder.build();
        return this.myUserRepository.findAll(exp);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/rsql")
    @ResponseBody
    public List<User> findAllByRsql(@RequestParam(value = "search") final String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<User>());
        return this.dao.findAll(spec);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/myusers")
    @ResponseBody
    public Iterable<MyUser> findAllByWebQuerydsl(@QuerydslPredicate(root = MyUser.class) final Predicate predicate) {
        return this.myUserRepository.findAll(predicate);
    }

    // API - WRITE

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final User resource) {
        Preconditions.checkNotNull(resource);
        this.dao.save(resource);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/myusers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMyUser(@RequestBody final MyUser resource) {
        Preconditions.checkNotNull(resource);
        this.myUserRepository.save(resource);

    }

}
