package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.aha.sat.jpa.city.City;
import com.github.aha.sat.jpa.city.CityRepository;

@DataJpaTest
class CityRepositoryNamedQueryTests {

	@Autowired
	protected CityRepository cityRepository;

    @Test
	void findByNameAndCountry() {
		var country = "USA";

		List<City> result = cityRepository.findByNameAndCountry("% %", country);

		assertThat(result).hasSize(2);
        City newYork = result.get(0);
		verifyCity(newYork, "New York", country);
    }

    @Test
	void retrieveByName() {
		var name = "prague";

		var city = cityRepository.retrieveByName(name);
    	verifyCity(city, "Prague", "Czech Republic");
	}

	@Nested
	class FindByName {

		@Test
		void shouldFind() {
			var name = "Miami";

			var city = cityRepository.findByName(name);

			verifyCity(city, name, "USA");
		}

		@Test
		void shouldNotFind() {
			var name = "prague";

			var city = cityRepository.findByName(name);
			assertThat(city).isNull();
		}

    }

	private void verifyCity(City city, String name, String country) {
		assertThat(city.getName()).isEqualTo(name);
		assertThat(city.getCountry()).isEqualTo(country);
	}

}

