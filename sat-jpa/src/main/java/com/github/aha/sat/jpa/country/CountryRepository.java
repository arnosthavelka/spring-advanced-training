package com.github.aha.sat.jpa.country;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long>, CountryCustomRepository {

	Country findByName(String name);

}
