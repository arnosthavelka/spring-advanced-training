package com.github.aha.sat.jpa.country;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {

	private final CountryRepository repository;

	@Transactional(readOnly = true)
	public List<Country> findAllBy(String name) {
		return repository.findAllByNameLike("%" + name + "%");
	}

}
