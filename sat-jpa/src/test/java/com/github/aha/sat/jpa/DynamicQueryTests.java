package com.github.aha.sat.jpa;

import static com.github.aha.sat.jpa.repository.CitySpecifications.cityFromState;
import static com.github.aha.sat.jpa.repository.CitySpecifications.cityHasNoState;
import static com.github.aha.sat.jpa.repository.CitySpecifications.cityHasState;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import com.github.aha.sat.jpa.domain.City;

class DynamicQueryTests extends AbstractCityTests {

    @Test
	void testCitiesWithState() {
		List<City> result = cityRepository.findAll(cityHasState(), Sort.by("country", "name"));
		assertThat(result.size()).isEqualTo(9);
		City city = result.get(0);
		assertThat(city.getName()).isEqualTo("Brisbane");
		assertThat(city.getCountry()).isEqualTo("Australia");
    }

    @Test
	void testCitiesWithoutStateInUsa() {
		List<City> result = cityRepository.findAll(cityHasNoState().and(cityFromState("USA")), Sort.by("country", "name"));
		assertThat(result.size()).isEqualTo(1);
		City city = result.get(0);
		assertThat(city.getName()).isEqualTo("Chicago");
		assertThat(city.getCountry()).isEqualTo("USA");
    }
}
