package com.github.aha.sat.rest.rest.hateoas;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.aha.sat.rest.domain.City;
import com.github.aha.sat.rest.rest.CityHateoasController;

@Component
public class CityResourceAssembler extends ResourceAssemblerSupport<City, CityResource> {

	public CityResourceAssembler() {
		super(CityHateoasController.class, CityResource.class);
	}

	@Override
	public CityResource toResource(City entity) {
		return new CityResource(entity);
	}

}
