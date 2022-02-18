package com.github.aha.sat.jpa.city;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends CityCustomRepository, JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

	City findByName(String name);
	
	List<City> findByNameAndCountry(String name, String country);

	City findByNameAndCountryAllIgnoringCase(String name, String country);

    Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name, String country, Pageable pageable);

	List<City> findByState(String state);

	default Specification<City> cityHasState() {
		return (r, q, cb) -> cb.notEqual(r.get(City_.state), "");
	}

	default Specification<City> cityHasNoState() {
		return (r, q, cb) -> cb.isNull(r.get(City_.state));
	}

	default Specification<City> cityFromCountry(final String country) {
		return (r, q, cb) -> cb.equal(r.get(City_.country), country);
	}

	@Query("SELECT c FROM City c WHERE LOWER(c.name) = LOWER(:name)")
	City retrieveByName(@Param("name") String name);

}
