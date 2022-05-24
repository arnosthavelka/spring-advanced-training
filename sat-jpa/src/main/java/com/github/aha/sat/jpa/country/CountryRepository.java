package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.country.QCountry.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface CountryRepository extends JpaRepository<Country, Long>, CountryCustomRepository, QuerydslPredicateExecutor<Country> {

	Country findByName(String name);

	List<Country> findAllByNameLike(String name);

	default Iterable<Country> findAllWithoutCities(String countryName) {
		return findAll(predicateWithoutCities(countryName));
	}

	default Predicate predicateWithoutCities(String countryName) {
		BooleanBuilder predicate = new BooleanBuilder()
				.and(getIfNotEmpty(countryName, v -> country.name.like("%" + v + "%")))
				.and(country.cities.isNotEmpty());
		return predicate;
	}

}
