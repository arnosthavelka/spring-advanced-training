package com.github.aha.sat.rest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.aha.sat.rest.AbstractTests;
import com.github.aha.sat.rest.repository.CityRepository;

@Transactional(readOnly = true)
public abstract class AbstractCityTests extends AbstractTests {

    @Autowired
    protected CityRepository cityRepository;

}
