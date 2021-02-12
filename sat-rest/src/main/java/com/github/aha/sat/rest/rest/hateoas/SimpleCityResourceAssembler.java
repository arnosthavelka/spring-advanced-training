package com.github.aha.sat.rest.rest.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.aha.sat.rest.domain.City;
import com.github.aha.sat.rest.rest.CityHateoasController;

@Component
public class SimpleCityResourceAssembler extends RepresentationModelAssemblerSupport<City, SimpleCityResource> {

	public SimpleCityResourceAssembler() {
		super(CityHateoasController.class, SimpleCityResource.class);
	}

	@Override
	public SimpleCityResource toModel(City entity) {
		SimpleCityResource resource = new SimpleCityResource(entity);
		resource.add(linkTo(methodOn(CityHateoasController.class).item(entity.getId())).withSelfRel());
		return resource;
	}

}
