package com.github.aha.sat.jpa.city;

import java.util.List;

import jakarta.persistence.Tuple;

import lombok.NonNull;

public interface CityCustomRepository {

    List<City> findAllCitiesBy(@NonNull String name, @NonNull String state, @NonNull String countryName);

    List<CityProjection> searchByCity(@NonNull String cityName);

    List<Tuple> countCitiesInCountriesLike(@NonNull String countryName);

}
