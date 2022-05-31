package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.country.QCountry.country;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.querydsl.jpa.impl.JPAQuery;

@DataJpaTest
class CountryRepositoryOtherTests {

	static final String USA = "USA";
	static final String AUSTRALIA = "Australia";

	@PersistenceContext
	private EntityManager em;

	@Test
	void getCountriesWithSingleCity() {
		var result = new JPAQuery<>(em)
				.select(country)
				.from(country)
				.where(country.cities.size().eq(1))
				.orderBy(country.name.asc())
				.fetch();

		assertThat(result)
				.hasSize(7)
				.first()
				.satisfies(c -> {
					assertThat(c.getName()).isEqualTo("Canada");
					assertThat(c.getCities()).hasSize(1);
				});
	}

	@Test
	void getCountriesWithoutCity() {
		var result = new JPAQuery<>(em)
				.select(country)
				.from(country)
				.where(country.cities.isEmpty())
				.orderBy(country.name.asc())
				.fetch();

		assertThat(result)
				.hasSize(1)
				.first()
				.satisfies(c -> {
					assertThat(c.getName()).isEqualTo("Belgium");
					assertThat(c.getCities()).isEmpty();
				});
	}

}
