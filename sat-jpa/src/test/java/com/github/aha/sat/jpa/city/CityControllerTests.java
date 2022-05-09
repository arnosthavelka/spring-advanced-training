package com.github.aha.sat.jpa.city;

import static java.util.List.of;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RestClientTest(CityController.class)
class CityControllerTests extends AbstractCityVerificationTest {

	@Autowired
	protected CityController controller;

	@MockBean
	private CityService service;

	@Test
	void searchForCityInAustralia() {
		var name = "Melbourne";
		var state = "Victoria";
		var country = "Australia";
		var cityInstance = City.builder().name(name).state(state).country(buildCountry(country)).build();
		when(this.service.findInAustraliaBy(name, state)).thenReturn(of(cityInstance));

		List<City> foundCities = this.controller.search(name, state, country);

		verifyFirstCityInCollection(foundCities, name, country);
	}

}
