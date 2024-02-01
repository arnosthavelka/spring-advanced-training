package com.github.aha.sat.rest.city.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.github.aha.sat.rest.city.City;
import com.github.aha.sat.rest.city.CityHateoasController;

import lombok.EqualsAndHashCode;

@Relation(collectionRelation = "cities")
@EqualsAndHashCode(callSuper = false)
public class CityEntityResource extends RepresentationModel<CityEntityResource> {

    private City city;

    public CityEntityResource(City city) {
        this.city = city;
        add(linkTo(methodOn(CityHateoasController.class).getOne(city.getId())).withSelfRel());
    }

    public String getName() {
        return city.getName();
    }

    public String getCountry() {
        return city.getCountry();
    }

}
