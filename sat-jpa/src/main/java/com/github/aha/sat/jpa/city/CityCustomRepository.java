package com.github.aha.sat.jpa.city;

import java.util.List;

import lombok.NonNull;

public interface CityCustomRepository {

	List<City> findAllCitiesBy(@NonNull String name, @NonNull String state, @NonNull String countryName);

	long countCitiesBy(String cityName, String cityState, @NonNull String countryName);

	List<javax.persistence.Tuple> countCitiesWithSpecificationByCountry(@NonNull String countryName);

}
