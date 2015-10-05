package com.asseco.aha.training.spring_advanced.jpa.repository;

import java.util.List;

import com.asseco.aha.training.spring_advanced.jpa.domain.City;

public interface CityService {

	// query is defined by interface implementation
	List<City> findByState(String state);

}
