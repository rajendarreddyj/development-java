package com.rajendarreddyj.spring.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.rajendarreddyj.spring.persistence.model.User;
import com.rajendarreddyj.spring.web.util.SearchOperation;
import com.rajendarreddyj.spring.web.util.SpecSearchCriteria;

public final class UserSpecificationsBuilder {

    private final List<SpecSearchCriteria> params;

    public UserSpecificationsBuilder() {
        this.params = new ArrayList<>();
    }

    // API

    public final UserSpecificationsBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return this.with(null, key, operation, value, prefix, suffix);
    }

    public final UserSpecificationsBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix,
            final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = (prefix != null) && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = (suffix != null) && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            this.params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<User> build() {

        if (this.params.size() == 0) {
            return null;
        }

        Specification<User> result = new UserSpecification(this.params.get(0));

        for (int i = 1; i < this.params.size(); i++) {
            result = this.params.get(i).isOrPredicate() ? Specifications.where(result).or(new UserSpecification(this.params.get(i)))
                    : Specifications.where(result).and(new UserSpecification(this.params.get(i)));

        }

        return result;
    }

    public final UserSpecificationsBuilder with(final UserSpecification spec) {
        this.params.add(spec.getCriteria());
        return this;
    }

    public final UserSpecificationsBuilder with(final SpecSearchCriteria criteria) {
        this.params.add(criteria);
        return this;
    }
}
