package com.rajendarreddyj.spring.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rajendarreddyj.spring.web.util.SearchCriteria;

public final class MyUserPredicatesBuilder {
    private final List<SearchCriteria> params;

    public MyUserPredicatesBuilder() {
        this.params = new ArrayList<>();
    }

    public MyUserPredicatesBuilder with(final String key, final String operation, final Object value) {
        this.params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (this.params.size() == 0) {
            return null;
        }

        final List<BooleanExpression> predicates = new ArrayList<>();
        MyUserPredicate predicate;
        for (final SearchCriteria param : this.params) {
            predicate = new MyUserPredicate(param);
            final BooleanExpression exp = predicate.getPredicate();
            if (exp != null) {
                predicates.add(exp);
            }
        }

        BooleanExpression result = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        return result;
    }
}
