package com.github.aha.sat.rest.city.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import com.github.aha.sat.rest.city.City;
import com.github.aha.sat.rest.city.CityHateoasController;

import lombok.Getter;

@Getter
@Relation(collectionRelation = "cities")
public class CityResource extends CityBaseResource {

	private long id;

	public CityResource(City city) {
		this.id = city.getId();
		setName(city.getName());
		setState(city.getState());
		setCountry(city.getCountry());
	}

	public EntityModel<CityResource> toResource() {
		return EntityModel.of(this,
				linkTo(methodOn(CityHateoasController.class).getOne(id)).withSelfRel(),
				linkTo(CityHateoasController.class).slash(id).withRel("delete"));
	}
}
