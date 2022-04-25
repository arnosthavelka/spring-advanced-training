package com.github.aha.sat.jpa.city;

import java.util.List;

import lombok.NonNull;

public interface CityCustomRepository {

	List<City> findAustraliaCitiesBy(@NonNull String name, @NonNull String state);
	List<City> findUsaCitiesBy(@NonNull String name, @NonNull String state);
	List<CityProjection> searchByCountry(@NonNull String countryName);
	long countCitiesBy(String cityName, String cityState, @NonNull String countryName);
	List<javax.persistence.Tuple> countCitiesWithSpecificationByCountry(@NonNull String countryName);
	List<com.querydsl.core.Tuple> countCitiesWithQuerydslByCountry(@NonNull String countryName);

}
