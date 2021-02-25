package com.github.aha.sat.jpa.city;

import org.springframework.data.jpa.domain.Specification;

public interface CitySpecifications {

	default Specification<City> cityHasState() {
		return (r, q, cb) -> cb.notEqual(r.get("state"), "");
    }

	default Specification<City> cityHasNoState() {
		return (r, q, cb) -> cb.isNull(r.get("state"));
    }

	default Specification<City> cityFromCountry(final String country) {
		return (r, q, cb) -> cb.equal(r.get("country"), country);
    }
}
