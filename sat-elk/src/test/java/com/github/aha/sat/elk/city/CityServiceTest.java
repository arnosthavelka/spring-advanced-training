package com.github.aha.sat.elk.city;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.data.domain.Pageable.unpaged;

import java.util.UUID;

import org.elasticsearch.common.collect.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.aha.sat.elk.ElkException;

@ExtendWith(SpringExtension.class)
class CityServiceTest {

	private static final String CITY_ID = UUID.randomUUID().toString();
	private static final String CITY_COUNTRY = "Germany";

	@Mock
	CityRepository repository;

	@Mock
	ElasticsearchOperations esTemplate;

	@InjectMocks
	private CityService service;

	@Nested
	class FindById {

		@Test
		void shouldReturnCity() {
			City city = new City();
			given(repository.findById(CITY_ID)).willReturn(of(city));

			City result = service.findById(CITY_ID);

			assertThat(result).isSameAs(city);
			verify(repository).findById(CITY_ID);
		}

		@Test
		void failWhenCityIsNotFound() {
			assertThrows(ElkException.class, () -> service.findById(CITY_ID));

			verify(repository).findById(CITY_ID);
		}

	}

	@Test
	void searchByCountry() {
		City city = new City();
		given(repository.findByCountry(eq(CITY_COUNTRY), any())).willReturn(new PageImpl<City>(List.of(city)));

		Page<City> result = service.searchByCountry(CITY_COUNTRY, unpaged());

		assertThat(result.getContent().get(0)).isSameAs(city);
		verify(repository).findByCountry(eq(CITY_COUNTRY), any());
	}

}
