package com.github.aha.sat.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.aha.sat.jpa.city.City;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class QueryTests extends AbstractCityTests {

    @Test
	void countCities() {
        long count = cityRepository.count();

		assertThat(count, equalTo(13L));
    }

    @Test
	void paginateCities() {
		Page<City> page = cityRepository.findAll(PageRequest.of(0, 5));

        assertThat(page.getSize(), equalTo(5));
		assertThat(page.getTotalElements(), equalTo(13L));
        assertThat(page.getTotalPages(), equalTo(3));
		log.debug("\n### testPagingt output");
        for (City city : page.getContent()) {
			log.debug(city.toString());
        }
    }

    @Test
	void sortCities() {
		Page<City> page = cityRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "country", "name"));

        assertThat(page.getSize(), equalTo(5));
		log.debug("\n### testSorting output");
        for (City city : page.getContent()) {
			log.debug(city.toString());
        }
    }

    @Test
	void getCityByName() {
        City city = cityRepository.findByNameAndCountryAllIgnoringCase("Tokyo", "Japan");

        assertThat(city.getName(), equalTo("Tokyo"));
        assertThat(city.getCountry(), equalTo("Japan"));
    }

}
