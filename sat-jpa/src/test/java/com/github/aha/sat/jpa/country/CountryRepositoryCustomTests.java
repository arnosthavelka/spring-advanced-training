package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.country.QCountry.country;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.aha.sat.jpa.city.City;

@DataJpaTest
class CountryRepositoryCustomTests {

	static final String USA = "USA";
	static final String AUSTRALIA = "Australia";

	@Autowired
	protected CountryRepository countryRepository;

	@Nested
	class FindAllCountriesHavingCity {

		@Test
		void exactValues() {
			var cityName = "San Francisco";

			var result = countryRepository.findAllCountriesHavingCity(cityName, "California");

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
			var result = countryRepository.findAllCountriesHavingCity("%an%", "%i%");

			assertThat(result)
					.singleElement()
					.satisfies(c -> {
						assertThat(c.getName()).isEqualTo(USA);
						assertThat(c.getCities()).map(City::getName).contains("Atlanta", "San Francisco");

					});
		}

	}

	@Test
	void searchByCountry() {
		var result = countryRepository.searchByCountry(AUSTRALIA);

		assertThat(result)
				.hasSize(3)
				.allSatisfy(p -> {
					assertThat(p.getId()).isPositive();
					assertThat(p.getName()).containsAnyOf("Brisbane", "Melbourne", "Sydney");
					assertThat(p.getState()).containsAnyOf("Queensland", "Victoria", "New South Wales");
					assertThat(p.getCountryName()).isEqualTo(AUSTRALIA);
				});

	}

	@Nested
	class CountBy {

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

	@Test
	void countCities() {
		var countries = countryRepository.countCitiesInCountriesContaining("a");

//		[[1, Australia, 3],
//		   [2, Canada, 1],
//		   [4, Japan, 1],
//		   [6, France, 1],
//		   [7, Spain, 1],
//		   [8, Switzerland, 1]]
		assertThat(countries)
				.hasSize(6)
				.first()
				.satisfies(t -> {
					assertThat(t.size()).isEqualTo(3);
					assertThat(t.get(country.name)).isEqualTo(AUSTRALIA);
					assertThat(t.get(2, Integer.class)).isEqualTo(3);
				});
	}

}
