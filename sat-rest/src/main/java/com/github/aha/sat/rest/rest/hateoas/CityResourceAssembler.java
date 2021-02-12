package com.github.aha.sat.rest.rest.hateoas;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.aha.sat.rest.domain.City;
import com.github.aha.sat.rest.rest.CityHateoasController;

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
