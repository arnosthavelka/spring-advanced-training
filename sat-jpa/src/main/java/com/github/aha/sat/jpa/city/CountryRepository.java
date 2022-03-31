package com.github.aha.sat.jpa.city;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.aha.sat.jpa.country.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	Country findByName(String name);

}
