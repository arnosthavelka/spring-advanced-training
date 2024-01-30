package com.github.aha.sat.rest.city;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.aha.sat.rest.city.resource.CityEntityResource;
import com.github.aha.sat.rest.city.resource.CityResource;

import jakarta.websocket.server.PathParam;

/**
 * Usage: get city detail - GET http://localhost:8080/city/hateoas/100 search with minimal
 * detail - GET http://localhost:8080/city/hateoas & contentTyp=application/json search -
 * GET http://localhost:8080/city/hateoas?country=Spain search & sort - GET
 * http://localhost:8080/city/hateoas?sorting=id delete city - DELETE
 * http://localhost:8080/city/hateoas/100
 */
@RestController
@RequestMapping("/city/hateoas")
public class CityHateoasController {

	@Autowired
	private CityService cityService;

	@GetMapping(consumes = APPLICATION_JSON_VALUE, produces = HAL_JSON_VALUE)
	public CollectionModel<CityEntityResource> search(@PathParam("country") String country,
			@PathParam("sorting") String sorting) {

		List<City> data = cityService.search(country, sorting);
		List<CityEntityResource> resources = data.stream().map(CityEntityResource::new).collect(toList());
		return CollectionModel.of(resources, linkTo(CityHateoasController.class).withSelfRel());
	}

	@GetMapping(produces = HAL_JSON_VALUE)
	public CollectionModel<EntityModel<CityResource>> searchAll(@PathParam("country") String country,
			@PathParam("sorting") String sorting) {

		List<City> data = cityService.search(country, sorting);
		List<EntityModel<CityResource>> resources = data.stream()
			.map(c -> new CityResource(c).toResource())
			.collect(toList());
		return CollectionModel.of(resources, linkTo(CityHateoasController.class).withSelfRel());
	}

	@GetMapping(value = "/{id}", produces = HAL_JSON_VALUE)
	public EntityModel<CityResource> getOne(@PathVariable("id") long id) {
		var city = cityService.getOne(id);
		return new CityResource(city).toResource();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable long id) {
		cityService.delete(id);
	}

}