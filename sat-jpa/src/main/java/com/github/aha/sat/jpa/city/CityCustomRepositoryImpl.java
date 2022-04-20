package com.github.aha.sat.jpa.city;

import static com.github.aha.sat.jpa.city.City_.country;
import static com.github.aha.sat.jpa.city.City_.state;
import static com.github.aha.sat.jpa.city.QCity.city;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.github.aha.sat.jpa.country.Country_;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CityCustomRepositoryImpl implements CityCustomRepository {

	@PersistenceContext
	private final EntityManager em;

	public List<City> findAustraliaCitiesBy(String cityName, String cityState) {
		var cb = em.getCriteriaBuilder();
		var query = cb.createQuery(City.class);
		Root<City> cityRoot = query.from(City.class);
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(cb.equal(cityRoot.get(City_.name), cityName));
		predicates.add(cb.equal(cityRoot.get(state), cityState));
		predicates.add(cb.equal(cityRoot.get(country).get(Country_.name), cb.literal("Australia")));

		query.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(query).getResultList();
	}

	public List<City> findUsaCitiesBy(String cityName, String cityState) {
		return new JPAQuery<City>(em)
				.from(city)
				.where(city.name.eq(cityName)
						.and(city.state.eq(cityState))
						.and(city.country.name.eq("USA")))
				.fetch();
	}

	public List<CityProjection> searchByCountry(String countryName) {
		return new JPAQuery<CityProjection>(em)
				.select(Projections.constructor(CityProjection.class, city.id, city.name, city.state, city.country.name))
				.from(city)
				.where(city.country.name.eq(countryName))
				.fetch();
	}

	public long countCitiesBy(String cityName, String cityState, String countryName) {
		JPAQuery<Long> query = new JPAQuery<>(em)
				.select(city.count())
				.from(city)
				.where(city.country.name.eq(countryName));
		if (nonNull(cityName)) {
			query.where(city.name.contains(cityName));
		}
		if (nonNull(cityState)) {
			query.where(city.state.like(cityState));
		}
		return query.fetchOne();
	}

}
