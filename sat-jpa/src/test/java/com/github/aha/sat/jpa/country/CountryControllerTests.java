package com.github.aha.sat.jpa.country;

import static org.mockito.Mockito.verify;
import static org.springframework.data.domain.Pageable.unpaged;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CountryControllerTests {

	@Mock
	CountryService service;

	@InjectMocks
	CountryController controller;

	@Test
	void search() {
		var name = "Japan";

		controller.search(name);

		verify(service).findAllBy(name);
	}

	@Test
	void findAllCountriesHavingCity() {
		var cityName = "aaa";
		var cityState = "bbb";

		controller.findAllCountriesHavingCity(cityName, cityState, unpaged());

		verify(service).findAllCountriesHavingCity(cityName, cityState, unpaged());
	}

}