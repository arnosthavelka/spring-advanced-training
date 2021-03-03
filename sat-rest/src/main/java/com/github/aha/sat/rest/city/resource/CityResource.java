package com.github.aha.sat.rest.city.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

import com.github.aha.sat.rest.city.City;
import com.github.aha.sat.rest.city.CityHateoasController;

import lombok.Getter;

@Getter
public class CityResource {

	private long id;
	private String name;
	private String state;
	private String country;

	public CityResource(City city) {
		this.id = city.getId();
		this.name = city.getName();
		this.state = city.getState();
		this.country = city.getCountry();
	}

	public EntityModel<CityResource> toResource() {
		return EntityModel.of(this,
				linkTo(methodOn(CityHateoasController.class).getOne(id)).withSelfRel(),
				linkTo(CityHateoasController.class).slash(id).withRel("delete"));
	}
}
