package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CityRepositorySpecificationTests extends AbstractCityVerificationTest {

    @Autowired
    protected CityRepository cityRepository;

    @Test
    void findAllWithState() {
        var cities = cityRepository.findAllWithState();

        assertThat(cities).hasSize(9);
        verifyFirstCityInCollection(cities, "Brisbane", AUSTRALIA);
    }

    @Test
    void noState() {
        var cities = cityRepository.findAllWithoutStateInUsa();

        assertThat(cities).hasSize(1);
        verifyFirstCityInCollection(cities, "New York", USA);
    }

}
