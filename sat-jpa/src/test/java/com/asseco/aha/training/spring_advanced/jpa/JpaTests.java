package com.asseco.aha.training.spring_advanced.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.spring_advanced.jpa.domain.City;
import com.asseco.aha.training.spring_advanced.jpa.repository.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaApplication.class)
// TODO:
// 1. JPA named queryhttp://docs.spring.io/spring-data/data-jpa/docs/1.0.x/reference/html/#jpa.query-methods.query-creation
// 2. advanced search http://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
public class JpaTests {

    private Logger LOG = LoggerFactory.getLogger(JpaTests.class);

    @Autowired
    CityRepository cityRepository;

    @Test
    public void testPaging() {
        Page<City> page = cityRepository.findAll(new PageRequest(0, 5));
        assertThat(5, equalTo(page.getSize()));
        assertThat(15L, equalTo(page.getTotalElements()));
        assertThat(3, equalTo(page.getTotalPages()));
        LOG.debug("\n### testPagingt output");
        for (City city : page.getContent()) {
            LOG.debug(city.toString());
        }
    }

    @Test
    public void testSorting() {
        Page<City> page = cityRepository.findAll(new PageRequest(0, 5, Sort.Direction.DESC, "country", "name"));
        assertThat(5, equalTo(page.getSize()));
        LOG.debug("\n### testSorting output");
        for (City city : page.getContent()) {
            LOG.debug(city.toString());
        }
    }

    @Test
    public void testCityByName() {
        City city = cityRepository.findByNameAndCountryAllIgnoringCase("Tokyo", "Japan");
        assertThat("Tokyo", equalTo(city.getName()));
        assertThat("Japan", equalTo(city.getCountry()));
    }

    @Test
    public void testQueryCityByName() {
        City city = cityRepository.findByName("Miami");
        assertThat("Miami", equalTo(city.getName()));
        assertThat("USA", equalTo(city.getCountry()));
    }

    @Test
    public void testQueryCityAndCountry() {
        List<City> result = cityRepository.findByNameAndCountry("% %", "USA");
        assertThat(2, equalTo(result.size()));

        City newYork = result.get(0);
        assertThat("New York", equalTo(newYork.getName()));
        assertThat("USA", equalTo(newYork.getCountry()));
    }

}
