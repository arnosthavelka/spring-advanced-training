package com.github.aha.sat.jpa.country;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.github.aha.sat.jpa.city.QCity;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CountryCustomRepositoryImpl implements CountryCustomRepository {

	@PersistenceContext
	private final EntityManager em;

	public List<Country> findAllByBy(@NonNull String cityName, @NonNull String cityState) {
		return new JPAQuery<Country>(em)
				.select(QCity.city.country)
				.from(QCity.city)
				.where(QCity.city.name.like(cityName)
						.and(QCity.city.state.like(cityState)))
				.fetch();
	}

}
