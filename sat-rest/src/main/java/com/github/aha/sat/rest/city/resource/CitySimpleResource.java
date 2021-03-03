package com.github.aha.sat.rest.city.resource;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.github.aha.sat.rest.city.City;

@Relation(collectionRelation = "cities")
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
