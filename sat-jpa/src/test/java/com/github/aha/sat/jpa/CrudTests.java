package com.github.aha.sat.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.github.aha.sat.jpa.domain.City;

@Transactional(readOnly = false)
@Rollback
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
        City city = cityRepository.findByName("Prague");
        long count = cityRepository.count();
        assertThat(count, equalTo(15L));
        cityRepository.delete(city);
        count = cityRepository.count();
        assertThat(count, equalTo(14L));
    }
}
