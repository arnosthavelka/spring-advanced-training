package com.asseco.aha.training.spring_advanced.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.asseco.aha.training.spring_advanced.jpa.domain.City;

public interface CityRepository extends Repository<City, Long> {

    Page<City> findAll(Pageable pageable);

    // Page<City> findAllOrderByName(Pageable pageable);

    Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name, String country, Pageable pageable);

    City findByNameAndCountryAllIgnoringCase(String name, String country);

}
