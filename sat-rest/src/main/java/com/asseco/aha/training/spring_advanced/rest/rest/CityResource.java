package com.asseco.aha.training.spring_advanced.rest.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;

import com.asseco.aha.training.spring_advanced.rest.domain.City;

public class CityResource extends Resource<City> {

	public CityResource(City entity) {
		super(entity, linkTo(methodOn(CityHateoasController.class).item(entity.getId())).withSelfRel());
	}

}
