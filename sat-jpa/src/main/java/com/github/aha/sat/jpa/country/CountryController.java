package com.github.aha.sat.jpa.country;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Usage: search: http://localhost:8080/countries?name=an</li>
 */
@RestController
@RequestMapping(value = "/countries", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CountryController {

	private final CountryService service;

	@GetMapping
	public List<Country> search(String name) {
		return service.findAllBy(name);
	}

}
