package com.github.aha.sat.elk.city;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
	public Collection<City> search(City searchDto, Pageable pageable) {
		return service.search(searchDto, pageable);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(String filename) {
		service.uploadFile(filename);
		return ResponseEntity.noContent().build();
	}

	public static <T> PageMetadata createResource(Page<T> page) {
		if (page == null) {
			return null;
		}
		return new PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
	}

}
