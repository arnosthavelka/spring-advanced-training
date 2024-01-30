package com.github.aha.sat.jpa.city;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Usage: - search GET
 * http://localhost:8080/cities?name=Melbourne&state=Victoria&countryName=Australia
 */
@RestController
@RequestMapping(value = "/cities", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CityController {

	private final CityService service;

	@GetMapping
	public List<City> search(String name, String state, String countryName) {
		return service.findAllBy(name, state, countryName);
	}

}
