package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * See https://www.baeldung.com/rest-api-search-language-spring-data-querydsl
 */
@DataJpaTest
class CityRepositoryCustomTests extends AbstractCityVerificationTest {

	@Autowired
	protected CityRepository cityRepository;

	@Nested
	class FindAllCitiesByTest {

		@Test
		void exactValues() {
			var cityName = "San Francisco";
			var state = "California";

			var result = cityRepository.findAllCitiesBy(cityName, state, USA);

			assertThat(result).singleElement().satisfies(c -> {
				assertThat(c.getId()).isPositive();
				assertThat(c.getName()).isEqualTo(cityName);
				assertThat(c.getState()).isEqualTo(state);
				assertThat(c.getCountry().getName()).isEqualTo(USA);
			});
		}

		@Test
		void wildcard() {
			var result = cityRepository.findAllCitiesBy("%an%", "%i%", USA);

			assertThat(result).hasSize(2).allSatisfy(c -> {
				assertThat(c.getCountry().getName()).isEqualTo(USA);
				assertThat(c.getName()).containsAnyOf("Atlanta", "San Francisco");
			});
		}

	}

	@Test
	void searchByCity() {
		var cityName = "London";

		var result = cityRepository.searchByCity(cityName);

		assertThat(result).singleElement().satisfies(c -> {
			assertThat(c.getId()).isPositive();
			assertThat(c.getName()).isEqualTo(cityName);
			assertThat(c.getState()).isNull();
			assertThat(c.getCountryName()).isEqualTo("United Kingdom");
		});
	}

	@Test
	void countCitiesWithSpecificationByCountry() {
		var result = cityRepository.countCitiesInCountriesLike(AUSTRALIA);

		assertThat(result).singleElement().satisfies(t -> {
			var tupleElements = t.getElements();
			assertThat(tupleElements).hasSize(3);
			assertThat((Long) t.get("countryId")).isPositive();
			assertThat(t.get(1, String.class)).isEqualTo(AUSTRALIA);
			assertThat(t.get(2, Long.class)).isEqualTo(3);
		});
	}

}
