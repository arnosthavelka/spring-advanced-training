package com.github.aha.sat.jpa.country;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {

	private final CountryRepository repository;

	@Transactional(readOnly = true)
	public List<Country> findAllBy(String name) {
		return repository.findByNameLikeIgnoreCase("%" + name + "%");
	}

	@Transactional(readOnly = true)
	public Page<Country> findAllCountriesHavingCity(@NonNull String cityName, @NonNull String cityState, Pageable pageable) {
		return repository.findAllCountriesHavingCity("%" + cityName + "%", "%" + cityState + "%", pageable);
	}

}
