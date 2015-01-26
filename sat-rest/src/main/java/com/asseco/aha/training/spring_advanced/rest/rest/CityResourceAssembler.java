package com.asseco.aha.training.spring_advanced.rest.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.asseco.aha.training.spring_advanced.rest.domain.City;

@Component
public class CityResourceAssembler extends ResourceAssemblerSupport<City, CityResource> {

	public CityResourceAssembler() {
		super(CityHateoasController.class, CityResource.class);
	}

	@Override
	public CityResource toResource(City entity) {
		CityResource resource = instantiateResource(entity);
		resource.setCity(entity);

		resource.add(linkTo(methodOn(CityHateoasController.class).item(entity.getId())).withSelfRel());
		resource.add(linkTo(CityHateoasController.class).slash(entity.getId()).withRel("delete"));

		return resource;
	}

}
