package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.city.QCity.city;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.aha.sat.jpa.city.City;

@DataJpaTest
class CountryRepositoryTests {

	static final String USA = "USA";

	@Autowired
	protected CountryRepository countryRepository;

	@Test
	void findByName() {
		var countryName = "Japan";

		var result = countryRepository.findByName(countryName);

		assertThat(result.getId()).isEqualTo(4);
		assertThat(result.getName()).isEqualTo(countryName);
		assertThat(result.getCities()).hasSize(1);
	}

	@Test
	void findAllByNameLike() {
		var namePattern = "%an%";

		var result = countryRepository.findAllByNameLike(namePattern);

		assertThat(result).map(Country::getName).containsExactly("Canada", "Japan", "France", "Switzerland");
	}

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

	@Nested
	class CountCitiesWithQuerydslByCountryTest {

		@Test
		void countriesWithSingleCity() {
			var result = countryRepository.countCitiesInCountriesLike("%an%");

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
		void justUsa() {
			var result = countryRepository.countCitiesInCountriesLike(USA);

			assertThat(result)
					.hasSize(1)
					.first()
					.satisfies(t -> {
						assertThat(t.size()).isPositive();
						assertThat(t.get(city.country.id.as("countryId"))).isPositive(); 	// or t.get(0, Long.class)
						assertThat(t.get(city.country.name)).isEqualTo(USA); 				// or t.get(1, String.class)
						assertThat(t.get(city.country.count())).isEqualTo(5); 				// or t.get(2, Long.class)
					});
		}

	}
}
