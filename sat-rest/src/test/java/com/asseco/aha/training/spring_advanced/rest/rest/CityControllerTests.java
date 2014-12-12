package com.asseco.aha.training.spring_advanced.rest.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.asseco.aha.training.spring_advanced.rest.AbstractTests;
import com.asseco.aha.training.spring_advanced.rest.domain.City;

@WebAppConfiguration
@IntegrationTest
public class CityControllerTests extends AbstractTests {

    private void verifyCity(City city, long id, String name) {
        assertThat(city.getId(), equalTo(id));
        assertThat(city.getName(), equalTo(name));
    }

    @Test
    public void testCityById() {
        String URI = "http://localhost:8080/city/{id}";
        RestTemplate restTemplate = new RestTemplate();
        City city = restTemplate.getForObject(URI, City.class, "100");
        assertThat(city.getName(), equalTo("Prague"));
    }

    @Test
    public void testCityFindAll() {
        String URI = "http://localhost:8080/city/";
        RestTemplate restTemplate = new RestTemplate();
        City[] data = restTemplate.getForObject(URI, City[].class);
        assertThat(data.length, equalTo(6));

        verifyCity(data[0], 100L, "Prague");
    }

    @Test
    public void testCityFindAllWithSorting() {
        String URI = "http://localhost:8080/city/?sorting=name";
        RestTemplate restTemplate = new RestTemplate();
        City[] data = restTemplate.getForObject(URI, City[].class);
        assertThat(data.length, equalTo(6));

        verifyCity(data[0], 102L, "Barcelona");
    }

    @Test
    public void testCityFindByCountry() {
        String URI = "http://localhost:8080/city/?country=UK";
        RestTemplate restTemplate = new RestTemplate();
        City[] data = restTemplate.getForObject(URI, City[].class);
        assertThat(data.length, equalTo(1));

        verifyCity(data[0], 104L, "London");
    }

}
