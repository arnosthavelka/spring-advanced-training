package com.github.aha.sat.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.aha.sat.jpa.domain.City;

class NamedQueryTests extends AbstractCityTests {

	private void verifyCity(City city, String name, String country) {
		assertThat(city.getName(), equalTo(name));
        assertThat(city.getCountry(), equalTo(country));
	}

    @Test
	void testQueryCityByName() {
        City city = cityRepository.findByName("Miami");
        verifyCity(city, "Miami", "USA");
    }

    @Test
	void testEntityQueryCityAndCountry() {
        List<City> result = cityRepository.findByNameAndCountry("% %", "USA");
        assertThat(result.size(), equalTo(2));

        City newYork = result.get(0);
        verifyCity(newYork, "New York", "USA");
    }

    @Test
	void testInterfaceQueryRetrieveByName() {
    	City city = cityRepository.retrieveByName("prague");
    	verifyCity(city, "Prague", "Czech Republic");
    	
    	city = cityRepository.findByName("prague");
    	assertThat(city, is(nullValue()));
    }

}
