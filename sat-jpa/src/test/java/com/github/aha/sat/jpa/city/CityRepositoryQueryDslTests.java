package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * See https://www.baeldung.com/rest-api-search-language-spring-data-querydsl
 */
@DataJpaTest
class CityRepositoryQueryDslTests extends AbstractCityRepositoryTests {

	@Autowired
	protected CityRepository cityRepository;

	@Test
	void findByState() {
		long result = cityRepository.count(cityRepository.searchBy("Japan", null));

		assertThat(result).isEqualTo(1);
	}

}
