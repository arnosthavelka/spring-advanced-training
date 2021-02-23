package com.github.aha.sat.rest.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.github.aha.sat.rest.AbstractTests;
import com.github.aha.sat.rest.city.City;
import com.github.aha.sat.rest.city.CityRepository;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CityControllerTests extends AbstractTests {

	static final String ROOT_URL = "/city/";

	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	CityRepository repository;

    @Test
	public void getCityById() {
		City city = restTemplate.getForObject(ROOT_URL + "{id}", City.class, "100");

		assertThat(city.getName()).isEqualTo("Prague");
    }

    @Test
	public void getCityByCountry() {
		City[] data = restTemplate.getForObject(ROOT_URL + "?country=UK", City[].class);

		assertThat(data.length).isEqualTo(1);
		verifyCity(data[0], 104L, "London");
	}

	@Test
	public void findAllCities() {
		City[] data = restTemplate.getForObject(ROOT_URL, City[].class);

		assertThat(data.length).isEqualTo(6);
        verifyCity(data[0], 100L, "Prague");
    }

    @Test
	public void findAllSortedCities() {
		City[] data = restTemplate.getForObject(ROOT_URL + "?sorting=name", City[].class);

		assertThat(data.length).isEqualTo(6);
        verifyCity(data[0], 102L, "Barcelona");
    }

	@Test
	public void createCity() {
		long originalCount = repository.count();

		City city = new City("Berlin", "Germany", null);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(APPLICATION_JSON);
		var request = new HttpEntity<City>(city, headers);

		ResponseEntity<Void> responseEntity = restTemplate.postForEntity(ROOT_URL, request, Void.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(CREATED);
		assertThat(responseEntity.getHeaders().getLocation().toString()).isNotEmpty();
		assertThat(repository.count()).isGreaterThan(originalCount);
	}

	@Test
	public void updateCity() {
		var testState = "Test state";
		var cityId = 100L;

		ResponseEntity<City> responseEntity = restTemplate.exchange(ROOT_URL + cityId + "?state=" + testState, PUT, null, City.class);

		City updatedCity = responseEntity.getBody();
		assertThat(updatedCity.getId()).isEqualTo(cityId);
		assertThat(updatedCity.getState()).isEqualTo(testState);
	}

	private void verifyCity(City city, long id, String name) {
		assertThat(city.getId()).isEqualTo(id);
		assertThat(city.getName()).isEqualTo(name);
    }

}
