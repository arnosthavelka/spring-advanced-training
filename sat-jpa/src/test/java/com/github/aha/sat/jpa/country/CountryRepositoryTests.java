package com.github.aha.sat.jpa.country;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.aha.sat.jpa.city.City;

@DataJpaTest
class CountryRepositoryTests {

	@Autowired
	protected CountryRepository cityRepository;

	@Test
	void findByName() {
		var countryName = "Japan";

		var result = cityRepository.findByName(countryName);

		assertThat(result.getId()).isEqualTo(4);
		assertThat(result.getName()).isEqualTo(countryName);
		assertThat(result.getCities()).hasSize(1);
	}

	@Test
	void findAllByNameLike() {
		var namePattern = "%an%";

		var result = cityRepository.findAllByNameLike(namePattern);

		assertThat(result).map(Country::getName).containsExactly("Canada", "Japan", "France", "Switzerland");
	}

	@Nested
	class FindAllCountriesByTest {

		private static final String USA = "USA";

		@Test
		void exactValues() {
			var cityName = "San Francisco";

			var result = cityRepository.findAllCountriesBy(cityName, "California");

			assertThat(result)
					.hasSize(1)
					.first()
					.satisfies(c -> {
						assertThat(c.getId()).isEqualTo(3);
						assertThat(c.getName()).isEqualTo(USA);
						assertThat(c.getCities()).map(City::getName).contains(cityName);
					});
		}

		@Test
		void wildcard() {
			var result = cityRepository.findAllCountriesBy("%an%", "%i%");

			assertThat(result)
					.hasSize(2)
					.allSatisfy(c -> {
						assertThat(c.getName()).isEqualTo(USA);
						assertThat(c.getCities()).map(City::getName).contains("Atlanta", "San Francisco");
					});
		}

	}

}
