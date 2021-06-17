package com.github.aha.sat.elk.city;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.aha.sat.elk.ElkException;

@ExtendWith(SpringExtension.class)
class CityServiceTest {

	private static final String CITY_ID = UUID.randomUUID().toString();

	@Mock
	CityRepository repository;

	@Mock
	ElasticsearchOperations esTemplate;

	@InjectMocks
	private CityService service;

	@Nested
	class FindById {

		@Test
		public void shouldReturnCity() {
			City city = new City();
			given(repository.findById(CITY_ID)).willReturn(of(city));

			City result = service.findById(CITY_ID);

			assertThat(result).isSameAs(city);
			verify(repository).findById(CITY_ID);
		}

		@Test
		public void failWhenCityIsNotFound() {
			assertThrows(ElkException.class, () -> service.findById(CITY_ID));

			verify(repository).findById(CITY_ID);
		}

	}

}
