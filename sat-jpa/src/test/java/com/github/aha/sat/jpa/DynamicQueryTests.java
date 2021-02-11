package com.github.aha.sat.jpa;

import static com.github.aha.sat.jpa.repository.CitySpecifications.cityFromState;
import static com.github.aha.sat.jpa.repository.CitySpecifications.cityHasNoState;
import static com.github.aha.sat.jpa.repository.CitySpecifications.cityHasState;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import com.github.aha.sat.jpa.domain.City;

class DynamicQueryTests extends AbstractCityTests {

    @Test
	void testCitiesWithState() {
		List<City> result = cityRepository.findAll(cityHasState(), Sort.by("country", "name"));
        assertThat(result.size(), equalTo(9));
        City newYork = result.get(0);
        assertThat(newYork.getName(), equalTo("Brisbane"));
        assertThat(newYork.getCountry(), equalTo("Australia"));
    }

    @Test
	void testCitiesWithoutStateInUsa() {
		List<City> result = cityRepository.findAll(cityHasNoState().and(cityFromState("USA")), Sort.by("country", "name"));
        assertThat(result.size(), equalTo(1));
        City newYork = result.get(0);
        assertThat(newYork.getName(), equalTo("Chicago"));
        assertThat(newYork.getCountry(), equalTo("USA"));
    }
}
