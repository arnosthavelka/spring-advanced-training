package com.github.aha.sat.jpa;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.aha.sat.jpa.JpaApplication;
import com.github.aha.sat.jpa.repository.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaApplication.class)
@Transactional(readOnly = true)
public abstract class AbstractCityTests {

    protected Logger LOG = LoggerFactory.getLogger(AbstractCityTests.class);

    @Autowired
    protected CityRepository cityRepository;

}
