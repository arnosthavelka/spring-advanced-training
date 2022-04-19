package com.github.aha.sat.jpa.city;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Usage:
 * <ul>
 * <li>search by JPA Criteria: 	http://localhost:8080/cities/?name=Melbourne&state=Victoria&country=Australia</li>
 * <li>search by QueryDSL: 		http://localhost:8080/cities/?name=Atlanta&state=Georgia&country=USA</li>
 * </ul>
 */
@RestController
@RequestMapping(value = "/cities", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CityController {

	private final CityService service;

	@GetMapping
	public List<City> search(String name, String state, String country) {
		return switch (country) {
		case "Australia": {
			yield service.findInAustraliaBy(name, state);
		}
		case "USA": {
			yield service.findInUsaBy(name, state);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + country);
		};
	}

}
