package com.asseco.aha.training.spring_advanced.jpa;

import static com.asseco.aha.training.spring_advanced.jpa.repository.CitySpecifications.cityFromState;
import static com.asseco.aha.training.spring_advanced.jpa.repository.CitySpecifications.cityHasNoState;
import static com.asseco.aha.training.spring_advanced.jpa.repository.CitySpecifications.cityHasState;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import org.junit.Test;
import org.springframework.data.domain.Sort;

import com.asseco.aha.training.spring_advanced.jpa.domain.City;

// @see http://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
public class DynamicQueryTests extends AbstractCityTests {

    @Test
    public void testCitiesWithState() {
        List<City> result = cityRepository.findAll(cityHasState(), new Sort("country", "name"));
        assertThat(result.size(), equalTo(9));
        City newYork = result.get(0);
        assertThat(newYork.getName(), equalTo("Brisbane"));
        assertThat(newYork.getCountry(), equalTo("Australia"));
    }

    @Test
    public void testCitiesWithoutStateInUsa() {
        List<City> result = cityRepository.findAll(where(cityHasNoState()).and(cityFromState("USA")), new Sort("country", "name"));
        assertThat(result.size(), equalTo(1));
        City newYork = result.get(0);
        assertThat(newYork.getName(), equalTo("Chicago"));
        assertThat(newYork.getCountry(), equalTo("USA"));
    }
}
