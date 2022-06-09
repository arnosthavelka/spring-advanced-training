package com.github.aha.sat.jpa.country;

import static org.assertj.core.api.Assertions.assertThat;

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
	void getByName() {
		var countryName = "Japan";

		var result = countryRepository.getByName(countryName);

		assertThat(result.getId()).isEqualTo(4);
		assertThat(result.getName()).isEqualTo(countryName);
		assertThat(result.getCities()).hasSize(1);
	}

	@Test
	void findByNameLikeIgnoreCase() {
		var namePattern = "%an%";

		var result = countryRepository.findByNameLikeIgnoreCase(namePattern);

		assertThat(result).map(Country::getName).containsExactly("Canada", "Japan", "France", "Switzerland");
	}

	@Test
	void countByStateName() {
		long result = countryRepository.count(countryRepository.predicateWithoutCities());

		assertThat(result).isEqualTo(1);
	}

	@Test
	void findAllWithoutCities() {
		var iterableResult = countryRepository.findAllWithoutCities();
		var result = Streamable.of(iterableResult).toList();

		assertThat(result).hasSize(1);
	}

}
