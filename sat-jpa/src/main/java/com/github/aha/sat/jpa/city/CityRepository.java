package com.github.aha.sat.jpa.city;

import static com.github.aha.sat.jpa.city.City_.COUNTRY;
import static com.github.aha.sat.jpa.city.City_.NAME;
import static com.github.aha.sat.jpa.city.City_.country;
import static com.github.aha.sat.jpa.city.City_.state;
import static com.github.aha.sat.jpa.country.Country_.name;
import static org.springframework.data.domain.Sort.by;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import lombok.NonNull;

public interface CityRepository extends CityCustomRepository,
		JpaRepository<City, Long>, JpaSpecificationExecutor<City>, QueryByExampleExecutor<City> {

	City getByName(String name);
	
	City findByNameAndCountryNameAllIgnoringCase(@NonNull String name, @NonNull String country);

	List<City> findByNameLikeAndCountryName(@NonNull String name, @NonNull String country);

	Page<City> findByNameContainingAndCountryNameContainingAllIgnoringCase(String name, String country, Pageable pageable);

	default List<City> findAllWithState() {
		return findAll(specWithState(), by(COUNTRY, NAME));
	}

	default Specification<City> specWithState() {
		return (cityRoot, q, cb) -> cb.not(cb.isNull(cityRoot.get(state)));
	}

	default List<City> findAllWithoutStateInUsa() {
		return findAll(specWithoutState().and(specByCountry("USA")), by(COUNTRY, NAME));
	}

	default Specification<City> specWithoutState() {
		return (cityRoot, q, cb) -> cb.isNull(cityRoot.get(state));
	}

	default Specification<City> specByCountry(@NonNull String countryName) {
		return (cityRoot, q, cb) -> cb.equal(cityRoot.get(country).get(name), countryName);
	}

	@Query("SELECT c FROM City c WHERE LOWER(c.name) = LOWER(:name)")
	City retrieveByName(@Param("name") String name);

}
