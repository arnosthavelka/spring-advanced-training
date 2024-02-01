package com.github.aha.sat.jpa.city;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.github.aha.sat.jpa.country.CountryRepository;

/**
 * See https://www.baeldung.com/spring-data-query-by-example
 */
@DataJpaTest
class CityRepositoryExampleTests extends AbstractCityVerificationTest {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @Test
    void findByState() {
        var country = countryRepository.getByName(AUSTRALIA);
        var exampleCity = City.builder().state("Queensland").country(country).build();

        var cities = cityRepository.findAll(Example.of(exampleCity));

        verifyFirstCityInCollection(cities, "Brisbane", AUSTRALIA);
    }

    @Test
    void findByNameAndCountry() {
        var exampleCity = City.builder().name("Montreal").country(buildCountry("Canada")).build();

        var cityOptional = cityRepository.findOne(Example.of(exampleCity));

        verifyOptionalCity(cityOptional, "Montreal", "Canada");
    }

    @Test
    void findByWildcard() {
        var exampleCity = City.builder().name("an").country(buildCountry("usa")).build();
        var matcher = ExampleMatcher.matching()
            .withMatcher("country", exact())
            .withIgnoreCase()
            .withMatcher("name", contains());

        var cities = cityRepository.findAll(Example.of(exampleCity, matcher));

        verifyFirstCityInCollection(cities, "Atlanta", USA);
    }

}
