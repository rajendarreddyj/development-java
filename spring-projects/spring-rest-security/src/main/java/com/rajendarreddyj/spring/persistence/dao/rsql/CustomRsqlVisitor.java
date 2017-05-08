package com.rajendarreddyj.spring.persistence.dao.rsql;

import org.springframework.data.jpa.domain.Specification;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;

public class CustomRsqlVisitor<T> implements RSQLVisitor<Specification<T>, Void> {

    private GenericRsqlSpecBuilder<T> builder;

    public CustomRsqlVisitor() {
        this.builder = new GenericRsqlSpecBuilder<>();
    }

    @Override
    public Specification<T> visit(final AndNode node, final Void param) {
        return this.builder.createSpecification(node);
    }

    @Override
    public Specification<T> visit(final OrNode node, final Void param) {
        return this.builder.createSpecification(node);
    }

    @Override
    public Specification<T> visit(final ComparisonNode node, final Void params) {
        return this.builder.createSpecification(node);
    }

}
