package com.github.aha.sat.rest.city.resource;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.aha.sat.rest.city.City;
import com.github.aha.sat.rest.city.CityHateoasController;

@Component
public class CityResourceAssembler extends RepresentationModelAssemblerSupport<City, CityResource> {

	public CityResourceAssembler() {
		super(CityHateoasController.class, CityResource.class);
	}

	@Override
	public CityResource toModel(City entity) {
		return new CityResource(entity);
	}

}
