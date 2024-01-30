package com.github.aha.sat.rest.city;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

	City findByName(String name);

	List<City> findByCountry(String country, Sort sort);

}
