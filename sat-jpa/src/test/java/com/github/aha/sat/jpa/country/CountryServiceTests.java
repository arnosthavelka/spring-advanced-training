package com.github.aha.sat.jpa.country;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CountryServiceTests {

	@Mock
	CountryRepository repository;

	@InjectMocks
	CountryService service;

    @Test
	void findAllBy() {
		var namePattern = "Melbourne";

		service.findAllBy(namePattern);

		verify(repository).findAllByNameLike("%" + namePattern + "%");
    }

}
