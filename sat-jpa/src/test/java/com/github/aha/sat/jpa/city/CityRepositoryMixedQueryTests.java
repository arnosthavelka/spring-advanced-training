package com.github.aha.sat.jpa.city;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CityRepositoryMixedQueryTests {

	@Autowired
	protected CityRepository cityRepository;

    @Test
	void loadCitiesWithState() {
		List<City> result = cityRepository.findByState("California");

		assertThat(result.size(), equalTo(1));
        City sf = result.get(0);
		assertThat(sf.getName(), equalTo("San Francisco"));
		assertThat(sf.getCountry(), equalTo("USA"));
    }

}
