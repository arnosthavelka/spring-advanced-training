package com.github.aha.sat.jpa.country;

import static java.util.Objects.nonNull;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuerydslUtils {

    public static <O> O getIfNotEmpty(String value, Function<String, O> converter) {
        return nonNull(value) ? converter.apply(value) : null;
    }

    public static <T> Page<T> fetchPage(EntityManager em, JPAQuery<T> query, Pageable pageable) {
        var querydsl = new Querydsl(em, new PathBuilder<>(query.getType(), "alias"));
        JPQLQuery<T> paginatedQuery = querydsl.applyPagination(pageable, query);
        return PageableExecutionUtils.getPage(paginatedQuery.fetch(), pageable, paginatedQuery::fetchCount);
    }

    public static <T> Page<T> fetchProjectedPage(EntityManager em, JPQLQuery<T> query, Pageable pageable,
            Class<?> rootType) {
        var querydsl = new Querydsl(em, (new PathBuilderFactory()).create(rootType));
        JPQLQuery<T> paginatedQuery = querydsl.applyPagination(pageable, query);
        return PageableExecutionUtils.getPage(paginatedQuery.fetch(), pageable, paginatedQuery::fetchCount);
    }

}
