package com.github.aha.sat.rest.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.aha.sat.rest.domain.City;
import com.github.aha.sat.rest.rest.hateoas.CityResource;
import com.github.aha.sat.rest.rest.hateoas.CityResourceAssembler;
import com.github.aha.sat.rest.rest.hateoas.SimpleCityResource;
import com.github.aha.sat.rest.rest.hateoas.SimpleCityResourceAssembler;
import com.github.aha.sat.rest.service.CityService;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * https://github.com/spring-projects/spring-hateoas http://java.dzone.com/articles/spring-mvc-and-hateoas
 * http://stateless.co/hal_specification.html
 */
@RestController
@RequestMapping("/city/resources")
public class CityHateoasController {

    @Autowired
    private CityService cityService;

	@Autowired
	private CityResourceAssembler assembler;

	@Autowired
	private SimpleCityResourceAssembler simpleAssembler;

    /*
	 * http://localhost:8080/city/resources/ http://localhost:8080/city/resources/?country=Spain, http://localhost:8080/city/resources/?sorting=id
	 */
	@GetMapping(value = "/", produces = { "application/hal+json" })
	public CollectionModel<SimpleCityResource> list(
			@ApiParam(name = "country", required = false) @PathParam("country") String country,
            @ApiParam(name = "sorting", required = false) @PathParam("sorting") String sorting) {
		List<City> data = cityService.list(country, sorting);

		CollectionModel<SimpleCityResource> resources = simpleAssembler.toCollectionModel(data);
		return CollectionModel.of(resources, linkTo(CityHateoasController.class).withSelfRel());
    }

	@GetMapping(value = "/all", produces = { "application/hal+json" })
	public CollectionModel<CityResource> listAll(
			@ApiParam(name = "country", required = false) @PathParam("country") String country,
			@ApiParam(name = "sorting", required = false) @PathParam("sorting") String sorting) {
		List<City> data = cityService.list(country, sorting);

		CollectionModel<CityResource> resources = assembler.toCollectionModel(data);
		return CollectionModel.of(resources, linkTo(CityHateoasController.class).withSelfRel());
	}

	@GetMapping(value = "/{id}", produces = { "application/hal+json" })
	public CityResource item(@PathVariable("id") long id) {
        City city = cityService.item(id);
        if (city == null) {
            throw new CityNotFoundException(String.format("City [id=%d] was not found!", id));
        }
		return assembler.toModel(city);
    }

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id) {
		cityService.delete(id);
	}

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class CityNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public CityNotFoundException(String message) {
            super(message);
        }
    }

}