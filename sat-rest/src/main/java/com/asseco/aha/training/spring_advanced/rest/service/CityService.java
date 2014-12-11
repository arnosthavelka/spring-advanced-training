package com.asseco.aha.training.spring_advanced.rest.service;

import java.util.List;

import com.asseco.aha.training.spring_advanced.rest.domain.City;

public interface CityService {

    public abstract List<City> list(String country, String sorting);

    public abstract City item(long id);

}