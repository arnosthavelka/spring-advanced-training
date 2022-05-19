package com.github.aha.sat.jpa.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long>, CountryCustomRepository {

	Country findByName(String name);

	List<Country> findAllByNameLike(String name);

}
