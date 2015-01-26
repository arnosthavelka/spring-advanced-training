package com.asseco.aha.training.spring_advanced.rest.rest.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.asseco.aha.training.spring_advanced.rest.domain.City;
import com.asseco.aha.training.spring_advanced.rest.rest.CityHateoasController;

@Component
public class SimpleCityResourceAssembler extends ResourceAssemblerSupport<City, SimpleCityResource> {

	public SimpleCityResourceAssembler() {
		super(CityHateoasController.class, SimpleCityResource.class);
	}

	@Override
	public SimpleCityResource toResource(City entity) {
		SimpleCityResource resource = new SimpleCityResource(entity);
		resource.add(linkTo(methodOn(CityHateoasController.class).item(entity.getId())).withSelfRel());
		return resource;
	}

}
