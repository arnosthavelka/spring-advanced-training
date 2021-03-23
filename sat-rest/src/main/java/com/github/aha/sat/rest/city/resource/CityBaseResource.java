package com.github.aha.sat.rest.city.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CityBaseResource {

	@NotNull(message = "Name cannot be null")
	@Size(min = 5, max = 20, message = "Name must be between 5 and 20 characters")
	private String name;

	@Schema(description = "State where the city belogs")
	private String state;

	@NotNull(message = "Country cannot be null")
	@Schema(required = true, description = "Country where the city belogs") // it duplicates @NotNull information -> otherwise it will be hidden
	private String country;

}
