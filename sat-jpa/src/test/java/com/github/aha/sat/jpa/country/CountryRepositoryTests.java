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
						assertThat(c.getId()).isPositive();
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

	@Test
	void searchByCountry() {
		var countryName = "Australia";

		var result = cityRepository.searchByCountry(countryName);

		assertThat(result)
				.hasSize(3)
				.allSatisfy(p -> {
					assertThat(p.getId()).isPositive();
					assertThat(p.getName()).containsAnyOf("Brisbane", "Melbourne", "Sydney");
					assertThat(p.getState()).containsAnyOf("Queensland", "Victoria", "New South Wales");
					assertThat(p.getCountryName()).isEqualTo(countryName);
				});

	}

}
