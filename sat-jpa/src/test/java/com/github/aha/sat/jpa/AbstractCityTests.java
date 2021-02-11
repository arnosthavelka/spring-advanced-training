package com.github.aha.sat.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.github.aha.sat.jpa.repository.CityRepository;

@SpringBootTest(classes = JpaApplication.class)
@Transactional(readOnly = true)
public abstract class AbstractCityTests {

    @Autowired
    protected CityRepository cityRepository;

}
