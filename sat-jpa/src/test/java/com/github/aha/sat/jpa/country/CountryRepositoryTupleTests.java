package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.city.QCity.city;
import static com.github.aha.sat.jpa.country.QCountry.country;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.NonNull;

@DataJpaTest
class CountryRepositoryTupleTests {

	static final String USA = "USA";

	@PersistenceContext
	private EntityManager em;

	@Nested
	class SimpleTupleUsageTest {

		List<Tuple> countCitiesInCountriesLike(@NonNull String countryName) {
			return new JPAQuery<>(em)
					.select(city.country.id.as("countryId"), city.country.name, city.country.count())
					.from(city)
					.where(city.country.name.like(countryName))
					.groupBy(city.country)
					.fetch();
		}

		@Test
		void referenceByAttribute() {
			var result = countCitiesInCountriesLike("%an%");

			assertThat(result)
					.hasSize(4)
					.allSatisfy(t -> {
						assertThat(t.size()).isPositive();
						assertThat(t.get(city.country.id.as("countryId"))).isPositive();
						assertThat(t.get(city.country.name)).containsAnyOf("Canada", "Japan", "France", "Switzerland");
						assertThat(t.get(city.country.count())).isEqualTo(1);
					});
		}

		@Test
		void referrenceByIndexAndType() {
			var result = countCitiesInCountriesLike(USA);

			assertThat(result)
					.hasSize(1)
					.first()
					.satisfies(t -> {
						assertThat(t.size()).isPositive();
						assertThat(t.get(0, Long.class)).isPositive();
						assertThat(t.get(1, String.class)).isEqualTo(USA);
						assertThat(t.get(2, Long.class)).isEqualTo(5);
					});
		}

	}

	@Nested
	class PagingTest {

		Page<Tuple> findAllTuplesSortedBy(Pageable pageable) {
			var query = new JPAQuery<>(em)
					.select(city.country.id, city.country.name, city.id, city.name)
					.from(city)
					.orderBy(country.name.asc(), city.name.asc());
			return fetchPage(query, pageable, Tuple.class);
		}

		<T, R> Page<T> fetchPage(JPAQuery<T> query, Pageable pageable, Class<R> pathType) {
			// var querydsl = new Querydsl(em, (new PathBuilderFactory()).create(pathType));
			var querydsl = new Querydsl(em, new PathBuilder<>(pathType, "abc"));
			JPQLQuery<T> paginatedQuery = querydsl.applyPagination(pageable, query);
			return PageableExecutionUtils.getPage(paginatedQuery.fetch(), pageable, paginatedQuery::fetchCount);
		}

		@Test
		void unpagedResult() {
			var result = findAllTuplesSortedBy(Pageable.unpaged());

			assertThat(result)
					.hasSize(15)
					.first()
					.satisfies(t -> {
						assertThat(t.size()).isPositive();
						assertThat(t.get(city.country.id)).isPositive();
						assertThat(t.get(city.country.name)).isEqualTo("Australia");
						assertThat(t.get(city.id)).isPositive();
						assertThat(t.get(city.name)).isEqualTo("Brisbane");
					});
		}

		@Test
		void pagedResult() {
			var pageSize = 3;
			var result = findAllTuplesSortedBy(PageRequest.of(0, pageSize));

			assertThat(result)
					.hasSize(pageSize)
					.first()
					.satisfies(t -> {
						assertThat(t.size()).isPositive();
						assertThat(t.get(city.country.id)).isPositive();
						assertThat(t.get(city.country.name)).isEqualTo("Australia");
						assertThat(t.get(city.id)).isPositive();
						assertThat(t.get(city.name)).isEqualTo("Brisbane");
					});
		}

	}

}