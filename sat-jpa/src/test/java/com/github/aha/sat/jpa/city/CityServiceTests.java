package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CityServiceTests {

	@Autowired
	protected CityService service;

    @Test
	void getCityByName() {
		var cityOptional = service.findInAustraliaBy("Melbourne", "Victoria");

		assertThat(cityOptional.get()).satisfies(c -> {
			assertThat(c.getName()).isEqualTo("Melbourne");
			assertThat(c.getState()).isEqualTo("Victoria");
			assertThat(c.getCountry()).isEqualTo("Australia");
		});
    }

}
