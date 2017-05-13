package com.rajendarreddyj.spring.persistence.dao;

import java.util.logging.Logger;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.rajendarreddyj.spring.persistence.model.MyUser;
import com.rajendarreddyj.spring.web.util.SearchCriteria;

public class MyUserPredicate {
    private static final Logger logger = Logger.getAnonymousLogger();
    private SearchCriteria criteria;

    public MyUserPredicate() {

    }

    public MyUserPredicate(final SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate() {
        final PathBuilder<MyUser> entityPath = new PathBuilder<>(MyUser.class, "myUser");

        if (isNumeric(this.criteria.getValue().toString())) {
            logger.info("Nuumber");
            final NumberPath<Integer> path = entityPath.getNumber(this.criteria.getKey(), Integer.class);
            final int value = Integer.parseInt(this.criteria.getValue().toString());
            if (this.criteria.getOperation().equalsIgnoreCase(":")) {
                return path.eq(value);
            } else if (this.criteria.getOperation().equalsIgnoreCase(">")) {
                return path.goe(value);
            } else if (this.criteria.getOperation().equalsIgnoreCase("<")) {
                return path.loe(value);
            }
        } else {
            final StringPath path = entityPath.getString(this.criteria.getKey());
            if (this.criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(this.criteria.getValue().toString());
            }
        }
        return null;
    }

    public SearchCriteria getCriteria() {
        return this.criteria;
    }

    public void setCriteria(final SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public static boolean isNumeric(final String str) {
        try {
            Integer.parseInt(str);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }
}
