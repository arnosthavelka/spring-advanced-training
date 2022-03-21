package com.github.aha.sat.jpa.city;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

	private final CityRepository repository;

	public Optional<City> findInAustraliaBy(String name, String state) {
		return repository.findAustraliaCitiesBy(name, state).stream().findFirst();
	}

}
