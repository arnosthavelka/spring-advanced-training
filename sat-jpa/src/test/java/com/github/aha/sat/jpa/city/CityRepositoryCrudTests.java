package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.aha.sat.jpa.city.City;
import com.github.aha.sat.jpa.city.CityRepository;

@DataJpaTest
class CityRepositoryCrudTests {

	@Autowired
	protected CityRepository cityRepository;

	long totalCount = -1;

	@PostConstruct
	void init() {
		if (totalCount < 0) {
			totalCount = cityRepository.count();
		}
	}

    @Test
	void createEntity() {
		var city = City.builder().name("Frankfurt").country("Germany").build();

        cityRepository.save(city);

		assertThat(cityRepository.count()).isEqualTo(totalCount + 1);
    }

    @Test
	void deleteEntity() {
		var city = cityRepository.findByName("Prague");

        cityRepository.delete(city);

		assertThat(cityRepository.count()).isEqualTo(totalCount - 1);
    }

}
