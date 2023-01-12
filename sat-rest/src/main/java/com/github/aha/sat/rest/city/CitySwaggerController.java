package com.github.aha.sat.rest.city;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aha.sat.rest.city.resource.CityBaseResource;
import com.github.aha.sat.rest.city.resource.CityProjections.Basic;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

/**
 * Usage:
 * get city detail				- GET http://localhost:8080/city/swagger/105
 * search 						- GET http://localhost:8080/city/swagger?country=Spain
 * search & sort 				- GET http://localhost:8080/city/swagger?sorting=id
 * create city					- POST http://localhost:8080/city/swagger/100 + content
 * update city					- PUT http://localhost:8080/city/swagger/102?state=Valencia
 * delete city					- DELETE http://localhost:8080/city/swagger/100 
 */
@RestController
@RequestMapping("/city/swagger")
@Tag(name = "City", description = "Operations about city")
public class CitySwaggerController {

    @Autowired
    private CityService cityService;

	@GetMapping(value = "/{id}", produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	@Operation(summary = "Get city by ID", description = "Return the city or throw CityNotFoundException.", responses = {
			@ApiResponse(responseCode = "200", description = "Successful retrieval of city detail (full detail)"),
			@ApiResponse(responseCode = "404", description = "City with given ID does not exist")
	})
	public City getOne(@PathVariable("id") long id) {
		return cityService.getOne(id);
	}

	@GetMapping(produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	@JsonView(Basic.class)
	@Operation(summary = "Search cities", description = "Return all found cities with basic details.", responses = {
			@ApiResponse(responseCode = "200", description = "Successful retrieval of the list of cities (with simple detail)")
	})
	public List<City> search(
			@Parameter(name = "country", required = false) @PathParam("country") String country,
			@Parameter(name = "sorting", required = false) @PathParam("sorting") String sorting) {
        return cityService.search(country, sorting);
    }

	@PostMapping(consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	@ResponseStatus(CREATED)
	@Operation(summary = "Create a city", description = "Create a city based on data from CityBaseResource", responses = {
			@ApiResponse(responseCode = "201", description = "Successful creation of the city")
	})
	public void create(@Valid @RequestBody CityBaseResource resource, HttpServletRequest request, HttpServletResponse response) {
		Long id = cityService.save(null, resource).getId();
		response.addHeader("Location", getNewLocation(request, id));
	}

	@PutMapping(value = "/{id}")
	@Operation(summary = "Update the city", description = "Update the city with the data from CityBaseResource", responses = {
			@ApiResponse(responseCode = "200", description = "Successful update of the city"),
			@ApiResponse(responseCode = "404", description = "City with given ID does not exist")
	})
	public City update(@PathVariable long id, @Valid CityBaseResource resource, BindingResult bindingResult) {
		validateInputs(bindingResult);
		return cityService.save(id, resource);
    }

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(NO_CONTENT)
	@Operation(summary = "Delete the city", description = "Delete the city defined by ID)", responses = {
			@ApiResponse(responseCode = "204", description = "Successful deletion of the city")
	})
	public void delete(@PathVariable long id) {
		cityService.delete(id);
	}

	private void validateInputs(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new CityValidationException(buildValidationErrorMessage(bindingResult));
		}
	}

	private String buildValidationErrorMessage(BindingResult bindingResult) {
		var sb = new StringBuilder();
		sb.append("Validation errors found! [count=").append(bindingResult.getErrorCount()).append("]\n");
		sb.append("--------------------------------\n");
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			sb.append("- attribute=").append(fieldError.getObjectName()).append(".").append(fieldError.getField());
			sb.append(", message=").append(fieldError.getDefaultMessage()).append("\n");
		}

		return sb.toString();
	}

	private String getNewLocation(HttpServletRequest request, Long id) {
		var template = new UriTemplate(request.getRequestURL().append("/{childId}").toString());
		return template.expand(id).toASCIIString();

	}

}