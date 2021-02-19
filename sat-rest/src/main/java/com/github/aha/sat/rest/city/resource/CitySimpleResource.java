package com.github.aha.sat.rest.city.resource;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.RepresentationModel;

import com.github.aha.sat.rest.city.City;

@JsonRootName("city")
public class CitySimpleResource extends RepresentationModel<CitySimpleResource> {

	private String name;

	private String country;

	public CitySimpleResource(City entity) {
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
