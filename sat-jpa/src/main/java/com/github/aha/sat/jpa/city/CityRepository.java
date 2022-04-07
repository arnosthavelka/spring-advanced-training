package com.github.aha.sat.jpa.city;

import static com.github.aha.sat.jpa.city.City_.country;
import static com.github.aha.sat.jpa.city.City_.state;
import static com.github.aha.sat.jpa.city.QCity.city;
import static com.github.aha.sat.jpa.country.Country_.name;
import static java.util.Objects.nonNull;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import lombok.NonNull;

public interface CityRepository extends CityCustomRepository,
		JpaRepository<City, Long>, JpaSpecificationExecutor<City>, QueryByExampleExecutor<City>, QuerydslPredicateExecutor<City> {

	City findByName(String name);
	
	List<City> findByNameLikeAndCountryName(@NonNull String name, @NonNull String country);

	City findByNameAndCountryNameAllIgnoringCase(@NonNull String name, @NonNull String country);

	Page<City> findByNameContainingAndCountryNameContainingAllIgnoringCase(String name, String country, Pageable pageable);

	List<City> findByState(String state);

	default Specification<City> cityHasState() {
		return (cityRoot, q, cb) -> cb.not(cb.isNull(cityRoot.get(state)));
	}

	default Specification<City> cityHasNoState() {
		return (cityRoot, q, cb) -> cb.isNull(cityRoot.get(state));
	}

	default Specification<City> cityFromCountry(@NonNull String countryName) {
		return (cityRoot, q, cb) -> cb.equal(cityRoot.get(country).get(name), countryName);
	}

	default Predicate searchPredicateWithoutState(@NonNull String country, String name) {
		BooleanBuilder predicate = new BooleanBuilder()
				.and(city.country.name.like("%" + country + "%"))
				.and(city.state.isNull());
		if (nonNull(name)) {
			predicate.and(city.name.eq(name));
		}
		return predicate;
	}

	@Query("SELECT c FROM City c WHERE LOWER(c.name) = LOWER(:name)")
	City retrieveByName(@Param("name") String name);

}
