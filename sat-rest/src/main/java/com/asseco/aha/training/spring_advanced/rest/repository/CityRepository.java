package com.asseco.aha.training.spring_advanced.rest.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.asseco.aha.training.spring_advanced.rest.domain.City;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);

    List<City> findByCountry(String country, Sort sort);

}
