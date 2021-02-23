package com.github.aha.sat.jpa.city;

import java.util.List;

public interface CityService {

	// query is defined by interface implementation
	List<City> findByState(String state);

}
