package com.github.aha.sat.rest.city.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

import com.github.aha.sat.rest.city.City;
import com.github.aha.sat.rest.city.CityHateoasController;

public class CityResource extends EntityModel<City> {

	public CityResource(City entity) {
		super(entity, linkTo(methodOn(CityHateoasController.class).getOne(entity.getId())).withSelfRel(), linkTo(
				CityHateoasController.class).slash(entity.getId()).withRel("delete"));
	}
}
