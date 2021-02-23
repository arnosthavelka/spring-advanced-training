package com.github.aha.sat.jpa.city;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CityServiceImpl implements CityService {

	private static final String JPQL = "select c from City c where c.state = ?1";

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * @see com.github.aha.sat.jpa.repository.CityService#findByState(java.lang.String)
	 */
	public List<City> findByState(String state) {
		TypedQuery<City> query = em.createQuery(JPQL, City.class);
		query.setParameter(1, state);
		return query.getResultList();
	}

}
