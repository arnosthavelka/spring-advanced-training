package com.github.aha.sat.elk.city;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Usage:
 * get city detail									- GET http://localhost:8080/api/cities/HwB5aHgBiVYee_AkNeA6 
 * static search cities (with Page response)		- GET http://localhost:8080/api/cities/country/czech republic?sort=name,desc
 * dynamic search cities (with Page response)		- GET http://localhost:8080/api/cities/?name=be&country=Czech&subcountry=bohemia&size=5&sort=name
 * dynamic search cities (with SearchPage response)	- GET http://localhost:8080/api/cities/search_page?name=be&country=Czech&subcountry=bohemia&size=5&sort=name
 * dynamic search cities (with SearchHits response)	- GET http://localhost:8080/api/cities/search_hits?name=be&country=Czech&subcountry=bohemia&size=5&sort=name
 * upload data										- POST http://localhost:8080/api/cities/upload?filename=Z:/world-cities.csv
 */
@RestController
@RequestMapping(value = CityController.ROOT_PATH, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CityController {

	static final String ROOT_PATH = "/api/cities"; // NOSONAR

	@NonNull
	final CityService service;

	@GetMapping("/{id}")
	public City getById(@PathVariable String id) {
		return service.findById(id);
	}

	@GetMapping("/country/{country}")
	public Page<City> searchByCountry(@PathVariable("country") String country, Pageable pageable) {
		return service.searchByCountry(country, pageable);
	}

	@GetMapping
	public Page<City> search(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "country", required = false) String country,
			@RequestParam(name = "subcountry", required = false) String subcountry, Pageable pageable) {
		return service.search(name, country, subcountry, pageable);
	}

	@GetMapping("/search_page")
	public SearchPage<City> searchPage(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "country", required = false) String country,
			@RequestParam(name = "subcountry", required = false) String subcountry, Pageable pageable) {
		return service.searchPage(name, country, subcountry, pageable);
	}

	@GetMapping("/search_hits")
	public SearchHits<City> searchHits(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "country", required = false) String country,
			@RequestParam(name = "subcountry", required = false) String subcountry, Pageable pageable) {
		return service.searchHits(name, country, subcountry, pageable);
	}

	@PostMapping("/upload")
	@ResponseStatus(code = NO_CONTENT)
	public void uploadFile(String filename) {
		service.uploadFile(filename);
	}

}
