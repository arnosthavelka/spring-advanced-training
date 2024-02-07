package com.github.aha.sat.jpa.city;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository repository;

    @Transactional(readOnly = true)
    public List<City> findAllBy(@NonNull String cityName, @NonNull String cityState, @NonNull String countryName) {
        return repository.findAllCitiesBy(cityName, cityState, countryName);
    }

}
