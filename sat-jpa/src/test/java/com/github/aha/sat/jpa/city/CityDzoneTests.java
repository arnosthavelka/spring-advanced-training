package com.github.aha.sat.jpa.city;

import static com.github.aha.sat.jpa.city.City_.ID;
import static com.github.aha.sat.jpa.city.City_.country;
import static com.github.aha.sat.jpa.city.City_.name;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.ASC;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.github.aha.sat.jpa.country.Country_;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;

@DataJpaTest
class CityDzoneTests {

	@PersistenceContext
	EntityManager em;

	@Test
	void cityByName() {
		var cityName = "London";

		var city = getCityByName(cityName);

		assertThat(city.getName()).isEqualTo(cityName);
    }

	private City getCityByName(String cityName) {
		var cb = em.getCriteriaBuilder();
		var query = cb.createQuery(City.class);
		var cityRoot = query.from(City.class);

		query.select(cityRoot).where(cb.like(cityRoot.get(name), cityName));
		return em.createQuery(query).getSingleResult();
	}

	@Test
	void paginateCitiesInCountry() {
		var countryName = "USA";
		var pageable = PageRequest.of(0, 2, Sort.by(ASC, ID));

		var pagedResult = findCitiesByCountry(countryName, pageable);

		assertThat(pagedResult.getTotalElements()).isEqualTo(5);
		assertThat(pagedResult.getTotalPages()).isEqualTo(3);
        assertThat( pagedResult.getContent() )
	        .hasSize( 2 )
	        .map( City::getName )
				.containsExactly("Atlanta", "Chicago");
	}

	private Page<City> findCitiesByCountry(String countryName, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<City> query = cb.createQuery(City.class);
		Root<City> cityRoot = query.from(City.class);
		query.select(cityRoot).where(cb.equal(cityRoot.get(country).get(Country_.name), countryName));
		List<City> pagedData = paginateQuery(em.createQuery(query), pageable).getResultList();

		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<City> cityCountRoot = countQuery.from(City.class);
		countQuery.select(cb.count(cityCountRoot)).where(cb.equal(cityCountRoot.get(country).get(Country_.name), countryName));
		var totalCount = em.createQuery(countQuery).getSingleResult();

		return new PageImpl<>(pagedData, pageable, totalCount);
	}

	public static <T> TypedQuery<T> paginateQuery(TypedQuery<T> query, Pageable pageable) {
		if (pageable.isPaged()) {
			query.setFirstResult((int) pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		return query;
	}

	public static <T> Page<T> toPage(List<T> fromCollection, @NonNull Pageable pageable) {
		try {
			List<T> resources = emptyIfNull(fromCollection).stream()
					.skip(pageable.getOffset())
					.limit(pageable.getPageSize())
					.collect(toList());
			return new PageImpl<>(resources, pageable, fromCollection.size());
		} catch (UnsupportedOperationException uoe) {
			return new PageImpl<>(fromCollection, pageable, fromCollection.size());
		}
	}

}
