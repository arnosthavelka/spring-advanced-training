package com.github.aha.sat.jpa.country;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.util.Streamable;

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

	@Test
	void countByStateName() {
		long result = countryRepository.count(countryRepository.predicateWithoutCities("Japan"));

		assertThat(result).isEqualTo(1);
	}

	@Nested
	class FindAllWithoutCitiesTest {

		@Test
		void allCountries() {
			var iterableResult = countryRepository.findAllWithoutCities(null);
			var result = Streamable.of(iterableResult).toList();

			assertThat(result).hasSize(9);
		}

		@Test
		void specificCountry() {
			var countryName = "Switzerland";

			var iterableResult = countryRepository.findAllWithoutCities(countryName);
			var result = Streamable.of(iterableResult).toList();

			assertThat(result)
					.hasSize(1)
					.first()
					.satisfies(c -> {
						assertThat(c.getName()).isEqualTo(countryName);
						assertThat(c.getCities()).isNotEmpty();
					});
		}

	}

}
