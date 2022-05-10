package com.github.aha.sat.jpa.city;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityControllerTests {

	@Mock
	private CityService service;

	@InjectMocks
	protected CityController controller;

	@Test
	void search() {
		var name = "Melbourne";
		var state = "Victoria";
		var country = "Australia";

		controller.search(name, state, country);

		verify(service).findAllBy(name, state, country);
	}

}