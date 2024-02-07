package com.github.aha.sat.rest.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.github.aha.sat.rest.city.City;
import com.github.aha.sat.rest.city.CityRepository;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CitySwaggerControllerTests {

    static final String ROOT_URL = "/city/swagger";

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    CityRepository repository;

    @Test
    void getCityById() {
        City city = restTemplate.getForObject(ROOT_URL + "/{id}", City.class, "100");

        assertThat(city.getName()).isEqualTo("Prague");
    }

    @Test
    void getCityByCountry() {
        City[] data = restTemplate.getForObject(ROOT_URL + "?country=UK", City[].class);

        assertThat(data).hasSize(1);
        verifyCity(data[0], 104L, "London");
    }

    @Test
    void findAllCities() {
        City[] data = restTemplate.getForObject(ROOT_URL, City[].class);

        assertThat(data).hasSizeGreaterThan(4);
        verifyCity(data[0], 100L, "Prague");
    }

    @Test
    void findAllSortedCities() {
        City[] data = restTemplate.getForObject(ROOT_URL + "?sorting=name", City[].class);

        assertThat(data).hasSizeGreaterThan(4);
        verifyCity(data[0], 102L, "Barcelona");
    }

    @Test
    void createCity() {
        long originalCount = repository.count();

        var city = City.builder().name("Berlin").country("Germany").build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        var request = new HttpEntity<City>(city, headers);

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(ROOT_URL, request, Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(CREATED);
        assertThat(responseEntity.getHeaders().getLocation().toString()).isNotEmpty();
        assertThat(repository.count()).isGreaterThan(originalCount);
    }

    @ParameterizedTest
    @CsvSource(value = { "London, UK, England", "Chiredzi,Zimbabwe,Masvingo" })
    void updateCity(String name, String country, String state) {
        var cityId = 103L;
        var url = ROOT_URL + "/" + cityId + "?name=" + name + "&state=" + state + "&country=" + country;

        ResponseEntity<City> responseEntity = restTemplate.exchange(url, PUT, null, City.class);

        City updatedCity = responseEntity.getBody();
        assertThat(updatedCity.getId()).isEqualTo(cityId);
        assertThat(updatedCity.getName()).isEqualTo(name);
        assertThat(updatedCity.getCountry()).isEqualTo(country);
        assertThat(updatedCity.getState()).isEqualTo(state);
    }

    @Test
    void delete() {
        var cityId = 105L;

        ResponseEntity<Void> responseEntity = restTemplate.exchange(ROOT_URL + "/" + cityId, DELETE, null, Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(NO_CONTENT);
    }

    private void verifyCity(City city, long id, String name) {
        assertThat(city.getId()).isEqualTo(id);
        assertThat(city.getName()).isEqualTo(name);
    }

}
