package com.github.aha.sat.jpa;

import static com.github.aha.sat.jpa.city.CitySpecifications.cityFromCountry;
import static com.github.aha.sat.jpa.city.CitySpecifications.cityHasNoState;
import static com.github.aha.sat.jpa.city.CitySpecifications.cityHasState;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import com.github.aha.sat.jpa.city.City;

class DynamicQueryTests extends AbstractCityTests {

    @Test
	void testCitiesWithState() {
		List<City> result = cityRepository.findAll(cityHasState(), Sort.by("country", "name"));

		assertThat(result.size()).isEqualTo(7);
		City city = result.get(0);
		assertThat(city.getName()).isEqualTo("Brisbane");
		assertThat(city.getCountry()).isEqualTo("Australia");
    }

    @Test
	void testCitiesWithoutStateInUsa() {
		List<City> result = cityRepository.findAll(cityHasNoState().and(cityFromCountry("USA")), Sort.by("country", "name"));

		assertThat(result.size()).isEqualTo(1);
		City city = result.get(0);
		assertThat(city.getName()).isEqualTo("New York");
		assertThat(city.getCountry()).isEqualTo("USA");
    }
}
