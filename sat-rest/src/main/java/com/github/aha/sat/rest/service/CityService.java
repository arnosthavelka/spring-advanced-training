package com.github.aha.sat.rest.service;

import java.util.List;

import com.github.aha.sat.rest.domain.City;

public interface CityService {

    List<City> list(String country, String sorting);

	City item(Long id);

    long save(City city);

	void delete(Long id);

}