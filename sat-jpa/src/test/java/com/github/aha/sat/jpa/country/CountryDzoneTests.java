package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.country.QCountry.country;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.ASC;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.Assert;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
class CountryDzoneTests {

	@PersistenceContext
	EntityManager em;

	@Autowired
	CountryRepository repository;

	@Test
	void countryByName() {
		var countryName = "USA";

		var country = getCountryByName(countryName);

		assertThat(country.getName()).isEqualTo(countryName);
	}

	private Country getCountryByName(String countryName) {
		return new JPAQuery<Country>(em).select(country).from(country).where(country.name.like(countryName)).fetchOne();
	}

	@Test
	void paginateCitiesInCountry() {
		var countryNamePart = "a";
		var pageable = PageRequest.of(0, 3, Sort.by(ASC, country.id.getMetadata().getName()));

		var pagedResult = findCountries(countryNamePart, pageable);

		assertThat(pagedResult.getTotalElements()).isEqualTo(7);
		assertThat(pagedResult.getTotalPages()).isEqualTo(3);
		assertThat(pagedResult.getContent()).hasSize(3)
			.map(Country::getName)
			.containsExactly("Australia", "Canada", "USA");
	}

	private Page<Country> findCountries(String countryName, Pageable pageable) {
		var query = findCountriesQuery(country, countryName);
		applyPagination(pageable, query);
		return PageableExecutionUtils.getPage(query.fetch(), pageable,
				() -> findCountriesQuery(country.count(), countryName).fetchOne());
	}

	private <T> JPAQuery<T> findCountriesQuery(Expression<T> expression, String countryName) {
		return new JPAQuery<Country>(em).select(expression)
			.from(country)
			.where(country.name.containsIgnoreCase(countryName));
	}

	// org.springframework.data.jpa.repository.support.QuerydslRepositorySupport.getQuerydsl
	// org.springframework.data.jpa.repository.support.Querydsl.applyPagination
	<T> JPAQuery<T> applyPagination(Pageable pageable, JPAQuery<T> query) {

		Assert.notNull(pageable, "Pageable must not be null");
		Assert.notNull(query, "JPQLQuery must not be null");

		if (pageable.isUnpaged()) {
			return query;
		}

		query.offset(pageable.getOffset());
		query.limit(pageable.getPageSize());

		return query;
		// return applySorting(pageable.getSort(), query);
	}

}
