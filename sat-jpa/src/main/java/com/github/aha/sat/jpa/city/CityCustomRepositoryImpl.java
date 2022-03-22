package com.github.aha.sat.jpa.city;

import static com.github.aha.sat.jpa.city.City_.country;
import static com.github.aha.sat.jpa.city.City_.name;
import static com.github.aha.sat.jpa.city.City_.state;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CityCustomRepositoryImpl implements CityCustomRepository {

	@PersistenceContext
	private final EntityManager em;

	public List<City> findAustraliaCitiesBy(String nameArgument, String stateArgument) {
		var cb = em.getCriteriaBuilder();
		var query = cb.createQuery(City.class);
		Root<City> cityRoot = query.from(City.class);
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(cb.equal(cityRoot.get(name), nameArgument));
		predicates.add(cb.equal(cityRoot.get(state), stateArgument));
		predicates.add(cb.equal(cityRoot.get(country), cb.literal("Australia")));

		query.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(query).getResultList();
	}

}
