package com.github.aha.sat.jpa.city;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

/**
 * See https://www.baeldung.com/spring-data-query-by-example
 */
@DataJpaTest
class CityRepositoryExampleTests extends AbstractCityRepositoryTests {

	@Test
	void findByState() {
		var countryName = "Australia";
		var country = countryRepository.findByName(countryName);
		var exampleCity = City.builder().state("Queensland").country(country).build();

		List<City> result = cityRepository.findAll(Example.of(exampleCity));

		verifyFirstCityInCollection(result, "Brisbane", countryName);
	}

	@Test
	void findByNameAndCountry() {
		var exampleCity = City.builder()
				.name("Montreal")
				.country(buildCountry("Canada"))
				.build();

		Optional<City> optionalResult = cityRepository.findOne(Example.of(exampleCity));

		verifyOptionalCity(optionalResult, "Montreal", "Canada");
	}

	@Test
	void findByWildcard() {
		var exampleCity = City.builder()
				.name("an")
				.country(buildCountry("usa"))
				.build();
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("country", exact()).withIgnoreCase()
				.withMatcher("name", contains());

		List<City> optionalResult = cityRepository.findAll(Example.of(exampleCity, matcher));

		verifyFirstCityInCollection(optionalResult, "Atlanta", "USA");
	}

}
