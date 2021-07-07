package com.github.aha.sat.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.aha.sat.jpa.city.City;
import com.github.aha.sat.jpa.city.CityRepository;
import com.github.aha.sat.jpa.city.City_;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
class QueryTests {

	@Autowired
	protected CityRepository cityRepository;

    @Test
	void countCities() {
        long count = cityRepository.count();

		assertThat(count).isEqualTo(13);
    }

    @Test
	void paginateCities() {
		Page<City> page = cityRepository.findAll(PageRequest.of(0, 5));

		assertThat(page.getSize()).isEqualTo(5);
		assertThat(page.getTotalElements()).isEqualTo(13);
		assertThat(page.getTotalPages()).isEqualTo(3);
		log.debug("\n### testPaging output");
        for (City city : page.getContent()) {
			log.debug(city.toString());
        }
    }

    @Test
	void sortCities() {
		Page<City> page = cityRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, City_.COUNTRY, City_.NAME));

		assertThat(page.getSize()).isEqualTo(5);
		log.debug("\n### testSorting output");
        for (City city : page.getContent()) {
			log.debug(city.toString());
        }
    }

    @Test
	void getCityByName() {
        City city = cityRepository.findByNameAndCountryAllIgnoringCase("Tokyo", "Japan");

		assertThat(city.getName()).isEqualTo("Tokyo");
		assertThat(city.getCountry()).isEqualTo("Japan");
    }

}
