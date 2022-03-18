package com.github.aha.sat.jpa.city;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

/**
 * See https://www.baeldung.com/spring-data-query-by-example
 */
@DataJpaTest
class CityRepositoryExampleTests extends AbstractCityRepositoryTests {

	@Autowired
	protected CityRepository cityRepository;

	@Test
	void findByState() {
		var exampleCity = new City();
		exampleCity.setState("Queensland");

		List<City> result = cityRepository.findAll(Example.of(exampleCity));

		verifyFirstCityInCollection(result, "Brisbane", "Australia");
	}

//	private Example<City> buildFilterByExample(City city) {
//		ExampleMatcher matcher = ExampleMatcher.matching()
//				.withIgnorePaths("id");
//		// .withMatcher( "code", exact() )
//		// .withMatcher( "name",
//		// ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase() );
//		return Example.of(city, matcher);
//	}

}
