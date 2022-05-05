package com.github.aha.sat.jpa.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, Long>, CountryCustomRepository {

	Country findByName(String name);

	// https://hibernate.atlassian.net/browse/HHH-15142
	List<Country> findAllByNameLike(@Param("name") String name);

}
