package com.github.aha.sat.jpa.country;

import java.util.List;

import com.github.aha.sat.jpa.city.CityProjection;
import com.querydsl.core.Tuple;

import lombok.NonNull;

public interface CountryCustomRepository {

	List<Country> findAllCountriesHavingCity(@NonNull String cityName, @NonNull String cityState);

	List<CityProjection> searchByCountry(@NonNull String countryName);

	List<Tuple> countCitiesInCountriesContaining(@NonNull String countryName);

	long countBy(String cityName, String cityState, String countryName);

}
