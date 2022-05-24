package com.github.aha.sat.jpa.country;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.function.Function;

import com.github.aha.sat.jpa.city.CityProjection;
import com.querydsl.core.Tuple;

import lombok.NonNull;

public interface CountryCustomRepository {

	List<Country> findAllCountriesBy(@NonNull String cityName, @NonNull String cityState);

	List<CityProjection> searchByCountry(@NonNull String countryName);

	long countBy(String cityName, String cityState, String countryName);

	List<Tuple> countCitiesInCountriesLike(@NonNull String countryName);

	default <O> O getIfNotEmpty(String value, Function<String, O> converter) {
		return nonNull(value) ? converter.apply(value) : null;
	}

}
