package com.github.aha.sat.jpa.city;

import java.util.List;

public interface CityCustomRepository {

	List<City> findAustraliaCitiesBy(String name, String state);
	List<City> findUsaCitiesBy(String name, String state);
	long countCitiesBy(String cityName, String cityState, String countryName);

}
