package com.github.aha.sat.jpa.country;

import java.util.List;

import lombok.NonNull;

public interface CountryCustomRepository {

	List<Country> findAllByBy(@NonNull String cityName, @NonNull String cityState);

}
