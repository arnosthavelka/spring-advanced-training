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
class CityRepositoryQueryDslTests extends AbstractCityRepositoryTests {

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

}
