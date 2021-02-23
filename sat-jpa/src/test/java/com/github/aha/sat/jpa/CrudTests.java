package com.github.aha.sat.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.github.aha.sat.jpa.city.City;

@Transactional(readOnly = false)
@Rollback
class CrudTests extends AbstractCityTests {

    @Test
	void createEntity() {
		long originalCount = cityRepository.count();

        City city = new City("Frankfurt", "Germany", "");
        cityRepository.save(city);

		assertThat(cityRepository.count()).isEqualTo(originalCount + 1);
    }

    @Test
	void deleteEntity() {
		long originalCount = cityRepository.count();

        City city = cityRepository.findByName("Prague");
        cityRepository.delete(city);

		assertThat(cityRepository.count()).isEqualTo(originalCount - 1);
    }

}
