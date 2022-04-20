package com.github.aha.sat.jpa.city;

import java.util.List;

public interface CityCustomRepository {

	List<City> findAustraliaCitiesBy(String name, String state);
	List<City> findUsaCitiesBy(String name, String state);
	List<CityProjection> searchByCountry(String countryName);
	long countCitiesBy(String cityName, String cityState, String countryName);

}
