package com.asseco.aha.training.spring_advanced.rest.rest.hateoas;

import org.springframework.hateoas.ResourceSupport;

import com.asseco.aha.training.spring_advanced.rest.domain.City;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("city")
public class SimpleCityResource extends ResourceSupport {

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
