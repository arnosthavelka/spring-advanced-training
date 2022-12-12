package com.github.aha.sat.jpa.city;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.aha.sat.jpa.country.CountryRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
class CityRepositoryTests extends AbstractCityVerificationTest {

	static final int TOTAL_SIZE = 15;
	static long totalCount = -1;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CountryRepository countryRepository;

	@PostConstruct
	void init() {
		if (totalCount < 0) {
			totalCount = cityRepository.count();
		}
	}
	
    @Test
	void countCities() {
		assertThat(totalCount).isEqualTo(TOTAL_SIZE);
    }

	@Nested
	class FindAllTest {


		@Test
		void pagination() {
			var pageSize = 5;

			Page<City> page = cityRepository.findAll(PageRequest.of(0, pageSize));

			assertThat(page.getSize()).isEqualTo(pageSize);
			assertThat(page.getTotalElements()).isEqualTo(TOTAL_SIZE);
			assertThat(page.getTotalPages()).isEqualTo(3);
			log.debug("\n### testPaging output");
			for (City city : page.getContent()) {
				log.debug(city.toString());
			}
		}

		@Test
		void sorting() {
			Page<City> page = cityRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, City_.COUNTRY, City_.NAME));

			assertThat(page.getSize()).isEqualTo(5);
			log.debug("\n### testSorting output");
			for (City city : page.getContent()) {
				log.debug(city.toString());
			}
		}

	}

	@Nested
	class GetByNameTest {

		@Test
		void shouldFindEntity() {
			var name = "Miami";

			var city = cityRepository.getByName(name);

			verifyCity(city, name, USA);
		}

		@Test
		void shouldNotFindEntity() {
			var misspelledName = "prague";

			var city = cityRepository.getByName(misspelledName);

			assertThat(city).isNull();
		}

	}

	@Test
	void findByNameLikeAndCountryName() {
		List<City> result = cityRepository.findByNameLikeAndCountryName("% %", USA);

		assertThat(result).hasSize(2);
		verifyFirstCityInCollection(result, "New York", USA);
	}

	@Test
	void findByNameAndCountryNameAllIgnoringCase() {
		City city = cityRepository.findByNameAndCountryNameAllIgnoringCase("Tokyo", "Japan");

		assertThat(city.getName()).isEqualTo("Tokyo");
		assertThat(city.getCountry().getName()).isEqualTo("Japan");
	}

	@Test
	void findByNameContainingAndCountryNameContainingAllIgnoringCase() {
		var pageSize = 2;

		Page<City> page = cityRepository.findByNameContainingAndCountryNameContainingAllIgnoringCase("an", "usa", PageRequest.of(0, pageSize));

		assertThat(page.getSize()).isEqualTo(pageSize);
		assertThat(page.getTotalElements()).isEqualTo(2);
		assertThat(page.getTotalPages()).isEqualTo(1);
		assertThat(page.getContent())
				.satisfies(c -> log.debug("The page content:"))
				.allSatisfy(city -> log.debug(city.toString()));
	}

	@Test
	void retrieveByName() {
		var name = "prague";

		var city = cityRepository.retrieveByName(name);

		verifyCity(city, "Prague", "Czech Republic");
	}

	@Nested
	class ModificationTest {

		@Test
		void createEntity() {
			var country = countryRepository.getByName(AUSTRALIA);
			var city = City.builder()
					.name("Darwin")
					.state("North territory")
					.country(country)
					.build();
			country.getCities().add(city);

			countryRepository.save(country);

			assertThat(cityRepository.count()).isEqualTo(totalCount + 1);
		}

		@Test
		void deleteEntity() {
			var city = cityRepository.getByName("Prague");

			cityRepository.delete(city);

			assertThat(cityRepository.count()).isEqualTo(totalCount - 1);
		}

	}

}
