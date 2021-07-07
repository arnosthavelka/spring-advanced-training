package com.github.aha.sat.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import com.github.aha.sat.jpa.city.City;
import com.github.aha.sat.jpa.city.CityRepository;

@DataJpaTest
class DynamicQueryTests {

	@Autowired
	protected CityRepository cityRepository;

    @Test
	void shouldFindCitiesWithState() {
		List<City> result = cityRepository.findAll(cityRepository.cityHasState(), Sort.by("country", "name"));

		assertThat(result.size()).isEqualTo(7);
		verifyCity(result.get(0), "Brisbane", "Australia");
    }

    @Test
	void shouldFindUsaCitiesWithoutState() {
		List<City> result = cityRepository.findAll(
				cityRepository.cityHasNoState().and(cityRepository.cityFromCountry("USA")),
				Sort.by("country", "name"));

		assertThat(result.size()).isEqualTo(1);
		verifyCity(result.get(0), "New York", "USA");
    }

	private void verifyCity(City city, String name, String country) {
		assertThat(city.getName()).isEqualTo(name);
		assertThat(city.getCountry()).isEqualTo(country);
	}

}
