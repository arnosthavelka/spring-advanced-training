package com.asseco.aha.training.spring_advanced.rest.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.web.client.RestTemplate;

import com.asseco.aha.training.spring_advanced.rest.AbstractTests;
import com.asseco.aha.training.spring_advanced.rest.domain.City;

@IntegrationTest("server.port:0")
public class CityControllerTests extends AbstractTests {

	@Value("${local.server.port}")
    private int port;
	
    private void verifyCity(City city, long id, String name) {
        assertThat(city.getId(), equalTo(id));
        assertThat(city.getName(), equalTo(name));
    }

    @Test
    public void testCityById() {
        String URI = String.format("http://localhost:%s/city/{id}", port);
        RestTemplate restTemplate = new RestTemplate();
        City city = restTemplate.getForObject(URI, City.class, "100");
        assertThat(city.getName(), equalTo("Prague"));
    }

    @Test
    public void testCityFindAll() {
        String URI = String.format("http://localhost:%s/city/", port);
        RestTemplate restTemplate = new RestTemplate();
        City[] data = restTemplate.getForObject(URI, City[].class);
        assertThat(data.length, equalTo(6));

        verifyCity(data[0], 100L, "Prague");
    }

    @Test
    public void testCityFindAllWithSorting() {
        String URI = String.format("http://localhost:%s/city/?sorting=name", port);
        RestTemplate restTemplate = new RestTemplate();
        City[] data = restTemplate.getForObject(URI, City[].class);
        assertThat(data.length, equalTo(6));

        verifyCity(data[0], 102L, "Barcelona");
    }

    @Test
    public void testCityFindByCountry() {
        String URI = String.format("http://localhost:%s/city/?country=UK", port);
        RestTemplate restTemplate = new RestTemplate();
        City[] data = restTemplate.getForObject(URI, City[].class);
        assertThat(data.length, equalTo(1));

        verifyCity(data[0], 104L, "London");
    }

}
