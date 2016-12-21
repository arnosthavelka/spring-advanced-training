package com.github.aha.sat.jpa.repository;

import java.util.List;

import com.github.aha.sat.jpa.domain.City;

public interface CityService {

	// query is defined by interface implementation
	List<City> findByState(String state);

}
