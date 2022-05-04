package com.github.aha.sat.jpa.country;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.aha.sat.jpa.city.City;

@DataJpaTest
class CountryRepositoryTests {

	@Autowired
	protected CountryRepository cityRepository;

	@Test
	void findAllByBy() {
		var cityName = "San Francisco";

		var result = cityRepository.findAllByBy(cityName, "California");

		assertThat(result)
				.hasSize(1)
				.first()
				.satisfies(c -> {
					assertThat(c.getId()).isEqualTo(3);
					assertThat(c.getCities()).map(City::getName).contains(cityName);
				});
	}

}
