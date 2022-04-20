package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.util.Streamable;

/**
 * See https://www.baeldung.com/rest-api-search-language-spring-data-querydsl
 */
@DataJpaTest
class CityRepositoryQueryDslTests extends AbstractCityVerificationTest {

	@Autowired
	protected CityRepository cityRepository;

	@Test
	void countStates() {
		long result = cityRepository.count(cityRepository.searchPredicateWithoutState("Japan", null));

		assertThat(result).isEqualTo(1);
	}

	@Nested
	class FindAllTest {

		@Test
		void findAllCities() {
			var iterableResult = cityRepository.findAll(cityRepository.searchPredicateWithoutState("an", null));
			var result = Streamable.of(iterableResult).toList();

			assertThat(result)
					.hasSize(3)
					.map(City::getName)
					.contains("Tokyo", "Paris", "Bern");
		}

		@Test
		void findCityByName() {
			var iterableResult = cityRepository.findAll(cityRepository.searchPredicateWithoutState("an", "Bern"));
			var result = Streamable.of(iterableResult).toList();

			assertThat(result).first().satisfies(c -> verifyCity(c, "Bern", "Switzerland"));
		}

	}

	@Nested
	class SearchByCountryTest {

		@Test
		void simple() {
			var result = cityRepository.searchByCountry("Australia");

			assertThat(result).hasSize(3);
		}

	}

	@Nested
	class CountCitiesByTest {

		@Test
		void countByCountry() {
			var count = cityRepository.countCitiesBy(null, null, "Australia");

			assertThat(count).isEqualTo(3);
		}

		@Test
		void countByNameAndCountry() {
			var count = cityRepository.countCitiesBy("an", null, "USA");

			assertThat(count).isEqualTo(2);
		}

		@Test
		void countByStateAndCountry() {
			var count = cityRepository.countCitiesBy(null, "%ni%", "USA");

			assertThat(count).isEqualTo(2);
		}

	}

}
