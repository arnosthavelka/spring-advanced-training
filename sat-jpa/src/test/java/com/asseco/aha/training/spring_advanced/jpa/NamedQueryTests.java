package com.asseco.aha.training.spring_advanced.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Test;

import com.asseco.aha.training.spring_advanced.jpa.domain.City;

public class NamedQueryTests extends AbstractCityTests {

    @Test
    public void testQueryCityByName() {
        City city = cityRepository.findByName("Miami");
        assertThat(city.getName(), equalTo("Miami"));
        assertThat(city.getCountry(), equalTo("USA"));
    }

    @Test
    public void testQueryCityAndCountry() {
        List<City> result = cityRepository.findByNameAndCountry("% %", "USA");
        assertThat(result.size(), equalTo(2));

        City newYork = result.get(0);
        assertThat(newYork.getName(), equalTo("New York"));
        assertThat(newYork.getCountry(), equalTo("USA"));
    }

}
