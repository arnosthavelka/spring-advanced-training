package com.github.aha.sat.elk.city;

import static com.github.aha.sat.elk.city.City.INDEX;
import static java.lang.Float.NaN;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.writeString;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.data.domain.Pageable.unpaged;
import static org.springframework.data.elasticsearch.core.TotalHitsRelation.EQUAL_TO;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsImpl;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.aha.sat.elk.ElkException;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@Slf4j
class CityServiceTest {

	private static final String CITY_ID = UUID.randomUUID().toString();
	private static final String CITY_COUNTRY = "Colombia";

	@Mock
	CityRepository repository;

	@Mock
	ElasticsearchOperations esTemplate;

	@InjectMocks
	@Spy
	private CityService service;

	@Test
	void uploadFile() throws IOException {
		var fileName = prepareTestCsv();

		service.uploadFile(fileName);

		verify(repository, times(2)).saveAll(any());
	}

	private String prepareTestCsv() throws IOException {
		Path tempFile = createTempFile("test-cities", ".csv");
		tempFile.toFile().deleteOnExit();
		log.info("Temp file : {}", tempFile);
		generateCsvContent(tempFile);
		return tempFile.toAbsolutePath().toString();
	}

	private void generateCsvContent(Path tempFile) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("name,country,subcountry,geonameid\n");
		for (int i = 0; i < 700; i++) {
			sb.append("name").append(i).append(",country").append(i).append(",subcountry").append(i).append(",").append(i).append(i).append("\n");
		}
		writeString(tempFile, sb.toString());
	}

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

	@ParameterizedTest
	@CsvSource(value = {
			"Armenia,Colombia,Quindío",
			"null,Colombia,Quindío",
			"Armenia,null,Quindío",
			"Armenia,Colombia,null",
			"null,null,null",
	}, nullValues = "null")
	void search(String name, String country, String subcountry) {
		Pageable pageable = unpaged();
		var cityHit = new SearchHit<City>(
				INDEX, UUID.randomUUID().toString(), null, NaN, null, null, null, null, null, null,
				new City(CITY_ID, name, country, subcountry, 666L));
		List<? extends SearchHit<City>> cities = List.of(cityHit);

		given(esTemplate.search(any(Query.class), eq(City.class), eq(IndexCoordinates.of(INDEX))))
				.willReturn(new SearchHitsImpl<City>(1, EQUAL_TO, NaN, null, cities, null));

		SearchHits<City> result = service.search(name, country, subcountry, pageable);

		assertThat(result.getTotalHits()).isEqualTo(1);
		verify(esTemplate).search(any(Query.class), eq(City.class), eq(IndexCoordinates.of(INDEX)));
	}

}
