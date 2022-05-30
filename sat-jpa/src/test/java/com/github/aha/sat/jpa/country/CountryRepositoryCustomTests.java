package com.github.aha.sat.jpa.country;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.aha.sat.jpa.city.City;

@DataJpaTest
class CountryRepositoryCustomTests {

	static final String USA = "USA";

	@Autowired
	protected CountryRepository countryRepository;

	@Nested
	class FindAllCountriesByTest {

		@Test
		void exactValues() {
			var cityName = "San Francisco";

			var result = countryRepository.findAllCountriesBy(cityName, "California");

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
			var result = countryRepository.findAllCountriesBy("%an%", "%i%");

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

		var result = countryRepository.searchByCountry(countryName);

		assertThat(result)
				.hasSize(3)
				.allSatisfy(p -> {
					assertThat(p.getId()).isPositive();
					assertThat(p.getName()).containsAnyOf("Brisbane", "Melbourne", "Sydney");
					assertThat(p.getState()).containsAnyOf("Queensland", "Victoria", "New South Wales");
					assertThat(p.getCountryName()).isEqualTo(countryName);
				});

	}

	@Nested
	class CountByTest {

		@Test
		void countAll() {
			var count = countryRepository.countBy(null, null, null);

			assertThat(count).isEqualTo(15);
		}

		@Test
		void countByCityName() {
			var count = countryRepository.countBy("an", null, null);

			assertThat(count).isEqualTo(3);
		}

		@Test
		void countByState() {
			var count = countryRepository.countBy(null, "%ni%", null);

			assertThat(count).isEqualTo(2);
		}

		@Test
		void countByCountry() {
			var count = countryRepository.countBy(null, null, USA);

			assertThat(count).isEqualTo(5);
		}

		@Test
		void countByCityNameAndState() {
			var count = countryRepository.countBy("a", "%n%", null);

			assertThat(count).isEqualTo(4);
		}

	}

}