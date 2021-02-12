package com.github.aha.sat.rest.rest.hateoas;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.RepresentationModel;

import com.github.aha.sat.rest.domain.City;

@JsonRootName("city")
public class SimpleCityResource extends RepresentationModel<SimpleCityResource> {

	private String name;

	private String country;

	public SimpleCityResource(City entity) {
		this.name = entity.getName();
		this.country = entity.getCountry();
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

}
