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
	void findInAustraliaBy() {
		var name = "Melbourne";
		var state = "Victoria";

		service.findInAustraliaBy(name, state);

		verify(repository).findAustraliaCitiesBy(name, state);
    }

}
