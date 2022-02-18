package com.github.aha.sat.jpa.city;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CityCustomRepositoryImpl implements CityCustomRepository {

	private static final String JPQL = "select c from City c where c.name = ?1 and c.state = ?2 and c.country = 'Australia'";

	@PersistenceContext
	private final EntityManager em;

	public List<City> findAustraliaCitiesBy(String name, String state) {
		TypedQuery<City> query = em.createQuery(JPQL, City.class);
		query.setParameter(1, name);
		query.setParameter(2, state);
		return query.getResultList();
	}

}
