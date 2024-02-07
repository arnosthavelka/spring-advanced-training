package com.github.aha.sat.rest.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.github.aha.sat.rest.RestApplication;
import com.github.aha.sat.rest.city.City;
import com.github.aha.sat.rest.city.CityRepository;

@SpringBootTest(classes = RestApplication.class)
@Transactional(readOnly = true)
class CityRepositoryTests {

    @Autowired
    protected CityRepository cityRepository;

    @Test
    void testCount() {
        long count = cityRepository.count();
        assertThat(count, equalTo(6L));
    }

    @Test
    void testCityById() {
        City city = cityRepository.getReferenceById(102L);

        assertAll(() -> assertThat(city.getName(), equalTo("Barcelona")),
                () -> assertThat(city.getCountry(), equalTo("Spain")),
                () -> assertThat(city.getState(), equalTo("Catalunya")));
    }

    @Test
    void testCityByName() {
        City city = cityRepository.findByName("Bern");

        assertAll(() -> assertThat(city.getId(), equalTo(103L)), () -> assertThat(city.getName(), equalTo("Bern")),
                () -> assertThat(city.getCountry(), equalTo("Switzerland")),
                () -> assertThat(city.getState(), equalTo("")));
    }

    @Test
    void testCitiesByCountry() {
        List<City> data = cityRepository.findByCountry("Czech Republic", Sort.by(Sort.Direction.ASC, "name"));

        assertThat(data.size(), equalTo(1));
        City city = data.get(0);
        assertAll(() -> assertThat(city.getId(), equalTo(100L)), () -> assertThat(city.getName(), equalTo("Prague")),
                () -> assertThat(city.getCountry(), equalTo("Czech Republic")),
                () -> assertThat(city.getState(), equalTo("")));
    }

}
