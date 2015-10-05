package com.asseco.aha.training.spring_advanced.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Test;

import com.asseco.aha.training.spring_advanced.jpa.domain.City;

public class MixedQueryTests extends AbstractCityTests {

    @Test
    public void testCitiesWithState() {
		List<City> result = cityRepository.findByState("CA");
		assertThat(result.size(), equalTo(1));
        City sf = result.get(0);
		assertThat(sf.getName(), equalTo("San Francisco"));
		assertThat(sf.getCountry(), equalTo("USA"));
    }

}
