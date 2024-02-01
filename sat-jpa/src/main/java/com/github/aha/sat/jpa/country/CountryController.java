package com.github.aha.sat.jpa.country;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Usage: - search by name GET http://localhost:8080/countries?name=an - search by cities
 * GET http://localhost:8080/countries/by-city?cityName=a&cityState=a&size=2&page=0
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

    @GetMapping("/by-city")
    public Page<Country> findAllCountriesHavingCity(@NonNull String cityName, @NonNull String cityState,
            Pageable pageable) {
        return service.findAllCountriesHavingCity(cityName, cityState, pageable);
    }

}
