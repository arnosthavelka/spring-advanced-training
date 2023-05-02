package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.city.QCity.city;
import static com.github.aha.sat.jpa.country.QCountry.country;
import static com.github.aha.sat.jpa.country.QuerydslUtils.getIfNotEmpty;
import static org.springframework.data.support.PageableExecutionUtils.getPage;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.github.aha.sat.jpa.city.CityProjection;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import lombok.NonNull;

@Repository
public class CountryCustomRepositoryImpl extends QuerydslRepositorySupport implements CountryCustomRepository {

	private final EntityManager em;

	public CountryCustomRepositoryImpl(EntityManager em) {
		super(Country.class);
		this.em = em;
	}

	public Page<Country> findAllCountriesHavingCity(@NonNull String cityName, @NonNull String cityState, Pageable pageable) {
		Long totalCount = findCountriesHavingCityQuery(city.country.count(), cityName, cityState).fetchOne();
		JPAQuery<Country> query = findCountriesHavingCityQuery(city.country, cityName, cityState);
		Assert.notNull(getQuerydsl(), "Querydsl must not be null");
		getQuerydsl().applyPagination(pageable, query);
		return getPage(query.fetch(), pageable, () -> totalCount);
	}
	
	private <T> JPAQuery<T> findCountriesHavingCityQuery(Expression<T> expression, String cityName, String cityState) {
		return new JPAQuery<Country>(em)
				.select(expression)				
				.from(city)
				.where(city.name.like(cityName)
						.and(city.state.like(cityState)));
	}

	public List<CityProjection> searchByCountry(@NonNull String countryName) {
		return new JPAQuery<CityProjection>(em)
				.select(Projections.constructor(CityProjection.class, city.id, city.name, city.state, city.country.name))
				.from(city)
				.where(city.country.name.eq(countryName))
				.fetch();
	}

	public List<Tuple> countCitiesInCountriesContaining(@NonNull String countryName) {
		return new JPAQuery<CityProjection>(em)
				.select(country.id, country.name, country.cities.size())
				.from(country)
				.where(country.name.contains(countryName))
				.fetch();
	}

	public long countBy(String cityName, String cityState, String countryName) {
		var query = new JPAQuery<>(em)
				.select(country.count())
				.from(country)
				.innerJoin(country.cities, city)
				.where(new BooleanBuilder()
						.and(getIfNotEmpty(cityName, city.name::contains))
						.and(getIfNotEmpty(cityState, city.state::like))
						.and(getIfNotEmpty(countryName, country.name::eq))
                        .getValue());
		return query.fetchOne();
	}

}
