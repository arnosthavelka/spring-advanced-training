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
	void findInAustraliaBy() {
		var cityOptional = service.findInAustraliaBy("Melbourne", "Victoria");

		assertThat(cityOptional.get()).satisfies(c -> {
			assertThat(c.getName()).isEqualTo("Melbourne");
			assertThat(c.getState()).isEqualTo("Victoria");
			assertThat(c.getCountry().getName()).isEqualTo("Australia");
		});
    }

	@Test
	void findInUsaBy() {
		var cityOptional = service.findInUsaBy("Atlanta", "Georgia");

		assertThat(cityOptional).get().satisfies(c -> {
			assertThat(c.getName()).isEqualTo("Atlanta");
			assertThat(c.getState()).isEqualTo("Georgia");
			assertThat(c.getCountry().getName()).isEqualTo("USA");
		});
	}

}
