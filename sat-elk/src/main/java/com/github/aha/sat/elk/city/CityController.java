package com.github.aha.sat.elk.city;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.websocket.server.PathParam;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Usage:
 * get city detail					- GET http://localhost:8080/api/cities/HwB5aHgBiVYee_AkNeA6 
 * search cities (with pagination)	- GET http://localhost:8080/api/cities/?name=be&country=Czech&subcountry=bohemia&size=5&sort=name
 */
@RestController
@RequestMapping(value = CityController.ROOT_CONTEXT, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CityController {

	public static final String ROOT_CONTEXT = "/api/cities";

	@NonNull
	final CityService service;

	@GetMapping("/{id}")
	public City getOne(@PathVariable String id) {
		return service.getOne(id).orElse(null);
	}

	@GetMapping
	public SearchHits<City> search(@PathParam("name") String name, @PathParam("country") String country,
			@PathParam("subcountry") String subcountry,
			Pageable pageable) {
		return service.search(name, country, subcountry, pageable);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(String filename) {
		service.uploadFile(filename);
		return ResponseEntity.noContent().build();
	}

}
