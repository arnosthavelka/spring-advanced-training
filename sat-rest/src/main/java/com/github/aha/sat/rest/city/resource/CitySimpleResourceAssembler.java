package com.github.aha.sat.rest.city.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.aha.sat.rest.city.City;
import com.github.aha.sat.rest.city.CityHateoasController;

@Component
public class CitySimpleResourceAssembler extends RepresentationModelAssemblerSupport<City, CitySimpleResource> {

	public CitySimpleResourceAssembler() {
		super(CityHateoasController.class, CitySimpleResource.class);
	}

	@Override
	public CitySimpleResource toModel(City entity) {
		CitySimpleResource resource = new CitySimpleResource(entity);
		resource.add(linkTo(methodOn(CityHateoasController.class).getOne(entity.getId())).withSelfRel());
		return resource;
	}

}
