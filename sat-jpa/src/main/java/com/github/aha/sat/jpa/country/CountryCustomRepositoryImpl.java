package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.city.QCity.city;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CountryCustomRepositoryImpl implements CountryCustomRepository {

	@PersistenceContext
	private final EntityManager em;

	public List<Country> findAllCountriesBy(@NonNull String cityName, @NonNull String cityState) {
		return new JPAQuery<Country>(em)
				.select(city.country)
				.from(city)
				.where(city.name.like(cityName)
						.and(city.state.like(cityState)))
				.fetch();
	}

}
