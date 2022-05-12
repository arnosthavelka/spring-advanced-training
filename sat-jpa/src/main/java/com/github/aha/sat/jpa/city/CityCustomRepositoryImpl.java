package com.github.aha.sat.jpa.city;

import static com.github.aha.sat.jpa.city.City_.country;
import static com.github.aha.sat.jpa.city.City_.name;
import static com.github.aha.sat.jpa.city.City_.state;
import static com.github.aha.sat.jpa.city.QCity.city;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.github.aha.sat.jpa.country.Country_;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CityCustomRepositoryImpl implements CityCustomRepository {

	@PersistenceContext
	private final EntityManager em;

	public List<City> findAllCitiesBy(@NonNull String cityName, @NonNull String cityState, @NonNull String countryName) {
		var cb = em.getCriteriaBuilder();
		var query = cb.createQuery(City.class);
		Root<City> cityRoot = query.from(City.class);
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(cb.like(cityRoot.get(name), cityName));
		predicates.add(cb.like(cityRoot.get(state), cityState));
		predicates.add(cb.equal(cityRoot.get(country).get(Country_.name), cb.literal(countryName)));

		query.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(query).getResultList();
	}

	public long countCitiesBy(String cityName, String cityState, @NonNull String countryName) {
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

	public List<Tuple> countCitiesInCountriesLike(@NonNull String countryName) {
		var cb = em.getCriteriaBuilder();
		var query = cb.createTupleQuery();
		var cityRoot = query.from(City.class);
		var countryJoin = cityRoot.join(City_.country);
		query.select(cb.tuple(countryJoin.get(Country_.id).alias("countryId"), countryJoin.get(Country_.name), cb.count(countryJoin))) // or use query.multiselect without the cb.tuple
				.where(cb.like(countryJoin.get(Country_.name), countryName))
				.groupBy(countryJoin);
		return em.createQuery(query).getResultList();
	}

}
