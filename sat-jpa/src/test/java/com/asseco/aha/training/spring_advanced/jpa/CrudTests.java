package com.asseco.aha.training.spring_advanced.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.asseco.aha.training.spring_advanced.jpa.domain.City;

@Transactional(readOnly = false)
@TransactionConfiguration(defaultRollback = true)
public class CrudTests extends AbstractCityTests {

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
