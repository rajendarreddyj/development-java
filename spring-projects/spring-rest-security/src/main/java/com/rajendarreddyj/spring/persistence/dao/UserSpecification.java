package com.rajendarreddyj.spring.persistence.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.rajendarreddyj.spring.persistence.model.User;
import com.rajendarreddyj.spring.web.util.SpecSearchCriteria;

public class UserSpecification implements Specification<User> {

    private SpecSearchCriteria criteria;

    public UserSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SpecSearchCriteria getCriteria() {
        return this.criteria;
    }

    @Override
    public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (this.criteria.getOperation()) {
        case EQUALITY:
            return builder.equal(root.get(this.criteria.getKey()), this.criteria.getValue());
        case NEGATION:
            return builder.notEqual(root.get(this.criteria.getKey()), this.criteria.getValue());
        case GREATER_THAN:
            return builder.greaterThan(root.<String> get(this.criteria.getKey()), this.criteria.getValue().toString());
        case LESS_THAN:
            return builder.lessThan(root.<String> get(this.criteria.getKey()), this.criteria.getValue().toString());
        case LIKE:
            return builder.like(root.<String> get(this.criteria.getKey()), this.criteria.getValue().toString());
        case STARTS_WITH:
            return builder.like(root.<String> get(this.criteria.getKey()), this.criteria.getValue() + "%");
        case ENDS_WITH:
            return builder.like(root.<String> get(this.criteria.getKey()), "%" + this.criteria.getValue());
        case CONTAINS:
            return builder.like(root.<String> get(this.criteria.getKey()), "%" + this.criteria.getValue() + "%");
        default:
            return null;
        }
    }

}
