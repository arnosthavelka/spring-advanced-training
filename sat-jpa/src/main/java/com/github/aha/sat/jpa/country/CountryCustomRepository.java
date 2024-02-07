package com.github.aha.sat.jpa.country;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.aha.sat.jpa.city.CityProjection;
import com.querydsl.core.Tuple;

import lombok.NonNull;

public interface CountryCustomRepository {

    Page<Country> findAllCountriesHavingCity(@NonNull String cityName, @NonNull String cityState, Pageable pageable);

    List<CityProjection> searchByCountry(@NonNull String countryName);

    List<Tuple> countCitiesInCountriesContaining(@NonNull String countryName);

    long countBy(String cityName, String cityState, String countryName);

}
