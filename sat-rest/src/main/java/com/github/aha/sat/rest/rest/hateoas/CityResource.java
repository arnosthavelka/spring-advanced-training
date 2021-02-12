package com.github.aha.sat.rest.rest.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

import com.github.aha.sat.rest.domain.City;
import com.github.aha.sat.rest.rest.CityHateoasController;

public class CityResource extends EntityModel<City> {

	public CityResource(City entity) {
		super(entity, linkTo(methodOn(CityHateoasController.class).item(entity.getId())).withSelfRel(), linkTo(
				CityHateoasController.class).slash(entity.getId()).withRel("delete"));
	}
}
