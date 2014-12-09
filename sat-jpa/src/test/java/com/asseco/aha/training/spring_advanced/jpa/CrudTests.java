package com.asseco.aha.training.spring_advanced.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.asseco.aha.training.spring_advanced.jpa.domain.City;
import com.asseco.aha.training.spring_advanced.jpa.repository.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaApplication.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CrudTests {

    @Autowired
    CityRepository cityRepository;

    @Test
    public void testCreate() {
        City city = new City("Frankfurt", "Germany", "");
        cityRepository.save(city);
        long count = cityRepository.count();
        assertThat(count, equalTo(16L));
    }

    @Test
    public void testDelete() {
        City city = new City("Wien", "Austria", "");
        city = cityRepository.saveAndFlush(city);
        long count = cityRepository.count();
        assertThat(count, equalTo(16L));
        cityRepository.delete(city);
        count = cityRepository.count();
        assertThat(count, equalTo(15L));
    }
}
