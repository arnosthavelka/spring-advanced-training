package com.github.aha.sat.jpa.city;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

/**
 * See https://www.baeldung.com/spring-data-query-by-example
 */
@DataJpaTest
class CityRepositoryExampleTests {

	@Autowired
	protected CityRepository cityRepository;

	@Test
	void findByState() {
		var exampleCity = new City();
		exampleCity.setState("Queensland");

		Iterable<City> iterableResult = cityRepository.findAll(Example.of(exampleCity));
		List<City> result = convertToList(iterableResult);

		verifyCity(result.get(0), "Brisbane", "Australia");
	}

	private <T> List<T> convertToList(Iterable<T> result) {
		return stream(result.spliterator(), false).collect(toList());
	}

//	private Example<City> buildFilterByExample(City city) {
//		ExampleMatcher matcher = ExampleMatcher.matching()
//				.withIgnorePaths("id");
//		// .withMatcher( "code", exact() )
//		// .withMatcher( "name",
//		// ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase() );
//		return Example.of(city, matcher);
//	}

	private void verifyCity(City city, String name, String country) {
		assertThat(city.getName()).isEqualTo(name);
		assertThat(city.getCountry()).isEqualTo(country);
	}

}
