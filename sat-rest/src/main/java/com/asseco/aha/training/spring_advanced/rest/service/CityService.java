package com.asseco.aha.training.spring_advanced.rest.service;

import java.util.List;

import com.asseco.aha.training.spring_advanced.rest.domain.City;

public interface CityService {

    List<City> list(String country, String sorting);

    City item(long id);

    long save(City city);

    void delete(long id);

}