package com.github.aha.sat.elk.city;

import static com.github.aha.sat.elk.city.CityController.ROOT_PATH;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CityController.class)
public class CityControllerTest {

	private static final String CITY_ID = UUID.randomUUID().toString();
	private static final String CITY_NAME = "Barcelona";
	private static final String CITY_COUNTRY = "Spain";
	private static final String CITY_SUBCOUNTRY = "Catalunya";

	@MockBean
	private CityService service;

	@Autowired
	private MockMvc mvc;

	@Test
	void listCities() throws Exception {
		when(this.service.findById(CITY_ID)).thenReturn(prepareCity(CITY_ID, CITY_NAME, CITY_COUNTRY, CITY_SUBCOUNTRY));
		mvc.perform(get(ROOT_PATH + "/" + CITY_ID))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
				.andExpect(jsonPath("id", is(CITY_ID)))
				.andExpect(jsonPath("name", is(CITY_NAME)))
				.andExpect(jsonPath("country", is(CITY_COUNTRY)))
				.andExpect(jsonPath("subcountry", is(CITY_SUBCOUNTRY)));
	}

	City prepareCity(String id, String name, String country, String subcountry) {
		var city = new City();
		city.setId(id);
		city.setName(name);
		city.setCountry(country);
		city.setSubcountry(subcountry);
		return city;
	}

}
