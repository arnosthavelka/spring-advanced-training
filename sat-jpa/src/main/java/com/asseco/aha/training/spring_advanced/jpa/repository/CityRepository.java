package com.asseco.aha.training.spring_advanced.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.asseco.aha.training.spring_advanced.jpa.domain.City;

public interface CityRepository extends JpaRepository<City, Long> {

    // Page<City> findAll(Pageable pageable); - just for Repository interface

    Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name, String country, Pageable pageable);

    City findByNameAndCountryAllIgnoringCase(String name, String country);

    City findByName(String name);

    List<City> findByNameAndCountry(String name, String country);

}
