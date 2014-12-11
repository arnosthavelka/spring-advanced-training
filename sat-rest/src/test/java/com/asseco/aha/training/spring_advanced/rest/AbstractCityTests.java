package com.asseco.aha.training.spring_advanced.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.asseco.aha.training.spring_advanced.rest.repository.CityRepository;

@Transactional(readOnly = true)
public abstract class AbstractCityTests extends AbstractTests {

    @Autowired
    protected CityRepository cityRepository;

}
