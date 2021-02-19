package com.github.aha.sat.rest.city;

import java.util.List;

public interface CityService {

    List<City> list(String country, String sorting);

	City item(Long id);

    long save(City city);

	void delete(Long id);

}