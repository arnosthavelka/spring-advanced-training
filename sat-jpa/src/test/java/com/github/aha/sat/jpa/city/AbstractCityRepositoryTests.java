package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

abstract class AbstractCityRepositoryTests {

	@Autowired
	CityRepository cityRepository;

	void verifyCity(City city, String name, String country) {
		assertThat(city.getName()).isEqualTo(name);
		assertThat(city.getCountry()).isEqualTo(country);
	}

	void verifyOptionalCity(Optional<City> optionalResult, String name, String country) {
		assertThat(optionalResult).get().satisfies(city -> {
			assertThat(city.getName(), equalTo(name));
			assertThat(city.getCountry(), equalTo(country));
		});
	}

	void verifyFirstCityInCollection(List<City> result, String name, String country) {
		assertThat(result)
				.first().satisfies(city -> {
					assertThat(city.getName(), equalTo(name));
					assertThat(city.getCountry(), equalTo(country));
				});
	}

}
