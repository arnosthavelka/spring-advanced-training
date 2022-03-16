package com.github.aha.sat.jpa.city;

import static com.github.aha.sat.jpa.city.City_.COUNTRY;
import static com.github.aha.sat.jpa.city.City_.NAME;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

@DataJpaTest
class CityRepositorySpecificationTests {

	@Autowired
	protected CityRepository cityRepository;

	@Test
	void cityHasState() {
		List<City> result = cityRepository.findAll(
				cityRepository.cityHasState(),
				Sort.by(COUNTRY, NAME));

		assertThat(result.size()).isEqualTo(7);
		verifyCity(result.get(0), "Brisbane", "Australia");
	}

	@Test
	void cityHasNoState() {
		List<City> result = cityRepository.findAll(
				cityRepository.cityHasNoState().and(cityRepository.cityFromCountry("USA")),
				Sort.by(COUNTRY, NAME));

		assertThat(result.size()).isEqualTo(1);
		verifyCity(result.get(0), "New York", "USA");
	}

	private void verifyCity(City city, String name, String country) {
		assertThat(city.getName()).isEqualTo(name);
		assertThat(city.getCountry()).isEqualTo(country);
	}

}
