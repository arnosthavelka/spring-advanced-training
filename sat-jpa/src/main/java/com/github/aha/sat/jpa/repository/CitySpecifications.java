package com.github.aha.sat.jpa.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.github.aha.sat.jpa.domain.City;

public class CitySpecifications {

    public static Specification<City> cityHasState() {
        return new Specification<City>() {

            @Override
            public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.notEqual(root.get("state"), "");
            }
        };
    }

    public static Specification<City> cityHasNoState() {
        return new Specification<City>() {

            @Override
            public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state"), "");
            }
        };
    }

    public static Specification<City> cityFromState(final String state) {
        return new Specification<City>() {

            @Override
            public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("country"), state);
            }
        };
    }
}
