package com.github.aha.sat.jpa.city;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

	private final CityRepository repository;

	@Transactional(readOnly = true)
	public List<City> findInAustraliaBy(String name, String state) {
		return repository.findAustraliaCitiesBy(name, state);
	}

}
