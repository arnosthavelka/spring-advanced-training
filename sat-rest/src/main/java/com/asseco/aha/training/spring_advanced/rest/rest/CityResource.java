package com.asseco.aha.training.spring_advanced.rest.rest;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.ResourceSupport;

import com.asseco.aha.training.spring_advanced.rest.domain.City;
import com.asseco.aha.training.spring_advanced.rest.rest.json.View;
import com.fasterxml.jackson.annotation.JsonView;

public class CityResource extends ResourceSupport {

	private City city;

	@JsonView(View.Summary.class)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	// public CityResource(City entity) {
		// super(entity, linkTo(methodOn(CityHateoasController.class).item(entity.getId())).withSelfRel());
	// }

}
