package com.github.aha.sat.jpa.city;

import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.reverseOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.github.aha.sat.jpa.country.CountryRepository;
import com.github.aha.sat.jpa.country.Country_;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
class CityRepositoryTests extends AbstractCityVerificationTest {

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
	
	@Nested
	class FindAll {

		@Test
		void pagination() {
			var pageSize = 5;
			var pageable = PageRequest.of(0, pageSize);

			Page<City> page = cityRepository.findAll(pageable);

			assertThat(page).hasSize(pageSize);
			assertThat(page.getTotalElements()).isEqualTo(totalCount);
			assertThat(page.getTotalPages()).isEqualTo(3);
		}

		@Test
		void sortingByNameAscending() {
			var pageable = PageRequest.of(0, 5, ASC, City_.NAME);
			
			Page<City> page = cityRepository.findAll(pageable);

			assertThat(page.getContent())
				.hasSize(5)
				.map(City::getName)
				.isSorted();
		}
		
		@Test
		void sortingByNameDescending() {
			var pageable = PageRequest.of(0, 5, DESC, City_.NAME);
			
			Page<City> page = cityRepository.findAll(pageable);

			assertThat(page.getContent())
				.hasSize(5)
				.map(City::getName)
				.isSortedAccordingTo( reverseOrder() );
		}
		
		@Test
		void sortingByCountryAndCityNames() {
			var countryNameSorting = City_.COUNTRY + "." + Country_.NAME;
			var pageable = PageRequest.of(0, 15, ASC, countryNameSorting, City_.NAME);
			
			Page<City> page = cityRepository.findAll(pageable);

			assertThat(page.getContent())
				.isSortedAccordingTo( getCountryNameComparator()
		                .thenComparing( City::getName ));
		}
		
		void sortingByStateAscending() {
			var pageable = PageRequest.of(0, 15, ASC, City_.STATE);
			
			Page<City> page = cityRepository.findAll(pageable);

			assertThat(page.getContent())
				.map(City::getState)
				.isSortedAccordingTo(nullsFirst(naturalOrder()));
		}
		
		void sortingByStateDescending() {
			var pageable = PageRequest.of(0, 15, DESC, City_.STATE);
			
			Page<City> page = cityRepository.findAll(pageable);

			assertThat(page.getContent())
				.map(City::getState)
				.isSortedAccordingTo(nullsLast(CASE_INSENSITIVE_ORDER));
		}
		
		private Comparator<City> getCountryNameComparator() {
			return ( c1, c2 ) -> c1.getCountry().getName().compareTo(c2.getCountry().getName());
		}

	}

	@Nested
	class GetByName {

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
		var pageable = PageRequest.of(0, pageSize);

		Page<City> page = cityRepository.findByNameContainingAndCountryNameContainingAllIgnoringCase("an", "usa", pageable);

		assertThat(page).hasSize(pageSize);
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

	@Test
	void saveNewEntity() {
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
	void delete() {
		var city = cityRepository.getByName("Prague");
		
		cityRepository.delete(city);
		
		assertThat(cityRepository.count()).isEqualTo(totalCount - 1);
	}

}
