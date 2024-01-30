package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.country.QCountry.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface CountryRepository
		extends CountryCustomRepository, JpaRepository<Country, Long>, QuerydslPredicateExecutor<Country> {

	Country getByName(String name);

	List<Country> findByNameLikeIgnoreCase(String name);

	default Iterable<Country> findAllWithoutCities() {
		return findAll(predicateWithoutCities(), country.name.asc());
	}

	default Predicate predicateWithoutCities() {
		return new BooleanBuilder().and(country.cities.isEmpty());
	}

}
