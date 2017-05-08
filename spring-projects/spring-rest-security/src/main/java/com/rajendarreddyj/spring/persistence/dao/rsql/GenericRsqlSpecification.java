package com.rajendarreddyj.spring.persistence.dao.rsql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;

    public GenericRsqlSpecification(final String property, final ComparisonOperator operator, final List<String> arguments) {
        super();
        this.property = property;
        this.operator = operator;
        this.arguments = arguments;
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        final List<Object> args = this.castArguments(root);
        final Object argument = args.get(0);
        switch (RsqlSearchOperation.getSimpleOperator(this.operator)) {

        case EQUAL: {
            if (argument instanceof String) {
                return builder.like(root.<String> get(this.property), argument.toString().replace('*', '%'));
            } else if (argument == null) {
                return builder.isNull(root.get(this.property));
            } else {
                return builder.equal(root.get(this.property), argument);
            }
        }
        case NOT_EQUAL: {
            if (argument instanceof String) {
                return builder.notLike(root.<String> get(this.property), argument.toString().replace('*', '%'));
            } else if (argument == null) {
                return builder.isNotNull(root.get(this.property));
            } else {
                return builder.notEqual(root.get(this.property), argument);
            }
        }
        case GREATER_THAN: {
            return builder.greaterThan(root.<String> get(this.property), argument.toString());
        }
        case GREATER_THAN_OR_EQUAL: {
            return builder.greaterThanOrEqualTo(root.<String> get(this.property), argument.toString());
        }
        case LESS_THAN: {
            return builder.lessThan(root.<String> get(this.property), argument.toString());
        }
        case LESS_THAN_OR_EQUAL: {
            return builder.lessThanOrEqualTo(root.<String> get(this.property), argument.toString());
        }
        case IN:
            return root.get(this.property).in(args);
        case NOT_IN:
            return builder.not(root.get(this.property).in(args));
        }

        return null;
    }

    // === private

    private List<Object> castArguments(final Root<T> root) {
        final List<Object> args = new ArrayList<>();
        final Class<? extends Object> type = root.get(this.property).getJavaType();

        for (final String argument : this.arguments) {
            if (type.equals(Integer.class)) {
                args.add(Integer.parseInt(argument));
            } else if (type.equals(Long.class)) {
                args.add(Long.parseLong(argument));
            } else {
                args.add(argument);
            }
        }

        return args;
    }

}
