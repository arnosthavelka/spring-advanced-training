package com.github.aha.sat.jpa.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityProjection {

	private Long id;

	private String name;

	private String state;

	private String countryName;

}
