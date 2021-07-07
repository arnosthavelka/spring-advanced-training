package com.github.aha.sat.jpa.city;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA implementation as:
 * <ol>
 * <li>Default implementation (methods defined by <code>JpaRepository</code> or
 * by this interface)</li>
 * <li>Mixed implementation (methods defined and implemented separately)</li>
 * <li>Dynamic queries from <code>JpaSpecificationExecutor</code> (used in
 * <code>DynamicQueryTests</code>)</li>
 * </ol>
 */
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name, String country, Pageable pageable);

	List<City> findByState(String state);

    City findByNameAndCountryAllIgnoringCase(String name, String country);

	/*
	 * Specifications
	 */

	default Specification<City> cityHasState() {
		return (r, q, cb) -> cb.notEqual(r.get("state"), "");
	}

	default Specification<City> cityHasNoState() {
		return (r, q, cb) -> cb.isNull(r.get("state"));
	}

	default Specification<City> cityFromCountry(final String country) {
		return (r, q, cb) -> cb.equal(r.get("country"), country);
	}

	/*
	 * Custom queries provided via <code>@Quury</code> or <code>@NamedQuery</code>
	 * in the entity.
	 */

	@Query("SELECT c FROM City c WHERE LOWER(c.name) = LOWER(:name)")
	City retrieveByName(@Param("name") String name);

	/**
	 * Next queries are defined by @NamedQuery (in the entity City)
	 */
    City findByName(String name);

    List<City> findByNameAndCountry(String name, String country);

}
