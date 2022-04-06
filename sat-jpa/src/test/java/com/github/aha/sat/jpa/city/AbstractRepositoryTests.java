package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.aha.sat.jpa.country.Country;

abstract class AbstractRepositoryTests {

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CountryRepository countryRepository;

	Country buildCountry(String countryName) {
		return Country.builder().name(countryName).build();
	}

	void verifyOptionalCity(Optional<City> optionalResult, String name, String countryName) {
		assertThat(optionalResult).get().satisfies(city -> verifyCity(city, name, countryName));
	}

	void verifyFirstCityInCollection(List<City> result, String name, String countryName) {
		assertThat(result)
				.first().satisfies(city -> verifyCity(city, name, countryName));
	}

	void verifyCity(City city, String name, String countryName) {
		assertThat(city.getName()).isEqualTo(name);
		assertThat(city.getCountry().getName()).isEqualTo(countryName);
	}

}
