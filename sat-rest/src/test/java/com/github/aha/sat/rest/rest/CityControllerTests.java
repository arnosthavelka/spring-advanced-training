package com.github.aha.sat.rest.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.github.aha.sat.rest.AbstractTests;
import com.github.aha.sat.rest.domain.City;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CityControllerTests extends AbstractTests {

	@Autowired
	TestRestTemplate restTemplate;
	
    private void verifyCity(City city, long id, String name) {
        assertThat(city.getId(), equalTo(id));
        assertThat(city.getName(), equalTo(name));
    }

    @Test
    public void testCityById() {
        City city = restTemplate.getForObject("/city/{id}", City.class, "100");
        assertThat(city.getName(), equalTo("Prague"));
    }

    @Test
    public void testCityFindAll() {
        City[] data = restTemplate.getForObject("/city/", City[].class);
        assertThat(data.length, equalTo(6));

        verifyCity(data[0], 100L, "Prague");
    }

    @Test
    public void testCityFindAllWithSorting() {
        City[] data = restTemplate.getForObject("/city/?sorting=name", City[].class);
        assertThat(data.length, equalTo(6));

        verifyCity(data[0], 102L, "Barcelona");
    }

    @Test
    public void testCityFindByCountry() {
        City[] data = restTemplate.getForObject("/city/?country=UK", City[].class);
        assertThat(data.length, equalTo(1));

        verifyCity(data[0], 104L, "London");
    }

}
