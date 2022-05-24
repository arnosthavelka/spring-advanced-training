package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * See https://www.baeldung.com/rest-api-search-language-spring-data-querydsl
 */
@DataJpaTest
class CityRepositoryCustomTests extends AbstractCityVerificationTest {

	static final String AUSTRALIA = "Australia";
	static final String USA = "USA";

	@Autowired
	protected CityRepository cityRepository;

	@Test
	void countCitiesWithSpecificationByCountry() {
		var countryName = "Australia";

		var result = cityRepository.countCitiesInCountriesLike(countryName);

		assertThat(result)
				.hasSize(1)
				.first()
				.satisfies(t -> {
					var tupleElements = t.getElements();
					assertThat(tupleElements).hasSize(3);
					assertThat((Long) t.get("countryId")).isPositive();
					assertThat(t.get(1, String.class)).isEqualTo(countryName);
					assertThat(t.get(2, Long.class)).isEqualTo(3);
				});
	}

}
