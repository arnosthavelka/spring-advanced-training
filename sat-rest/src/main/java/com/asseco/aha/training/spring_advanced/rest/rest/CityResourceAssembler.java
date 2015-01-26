package com.asseco.aha.training.spring_advanced.rest.rest;

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
		return new CityResource(entity);
	}

}
