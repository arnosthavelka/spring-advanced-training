package com.github.aha.sat.jpa.city;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityServiceTests {

	@Mock
	CityRepository repository;

	@InjectMocks
	protected CityService service;

	@Test
	void findAllBy() {
		var name = "Melbourne";
		var state = "Victoria";
		var countryName = "Australia";

		service.findAllBy(name, state, countryName);

		verify(repository).findAllCitiesBy(name, state, countryName);
	}

}
