package com.github.aha.sat.rest.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Test;
import org.springframework.data.domain.Sort;

import com.github.aha.sat.rest.domain.City;

public class CityRepositoryTests extends AbstractCityTests {

    @Test
    public void testCount() {
        long count = cityRepository.count();
        assertThat(count, equalTo(6L));
    }

    @Test
    public void testCityById() {
        City city = cityRepository.getOne(102L);
        assertThat(city.getName(), equalTo("Barcelona"));
        assertThat(city.getCountry(), equalTo("Spain"));
        assertThat(city.getState(), equalTo("Catalunya"));
    }

    @Test
    public void testCityByName() {
        City city = cityRepository.findByName("Bern");
        assertThat(city.getId(), equalTo(103L));
        assertThat(city.getName(), equalTo("Bern"));
        assertThat(city.getCountry(), equalTo("Switzerland"));
        assertThat(city.getState(), equalTo(""));
    }

    @Test
    public void testCitiesByCountry() {
        List<City> data = cityRepository.findByCountry("Czech Republic", new Sort(Sort.Direction.ASC, "name"));
        assertThat(data.size(), equalTo(1));
        // verify first city
        City city = data.get(0);
        assertThat(city.getId(), equalTo(100L));
        assertThat(city.getName(), equalTo("Prague"));
        assertThat(city.getCountry(), equalTo("Czech Republic"));
        assertThat(city.getState(), equalTo(""));
    }

}
