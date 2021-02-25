package com.github.aha.sat.jpa.city;

import org.springframework.data.jpa.domain.Specification;

public interface CitySpecifications {

	default Specification<City> cityHasState() {
		return (R, Q, CB) -> CB.notEqual(R.get("state"), "");
    }

	default Specification<City> cityHasNoState() {
		return (R, Q, CB) -> CB.isNull(R.get("state"));
    }

	default Specification<City> cityFromCountry(final String country) {
		return (R, Q, CB) -> CB.equal(R.get("country"), country);
    }
}
