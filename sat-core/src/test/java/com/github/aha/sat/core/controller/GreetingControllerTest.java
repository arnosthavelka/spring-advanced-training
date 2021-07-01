package com.github.aha.sat.core.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GreetingController.class)
@ComponentScan
class GreetingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testArny() throws Exception {
		mockMvc.perform(get("/arny"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_VALUE))
				.andExpect(content().string("Hello!"));
	}

	@Test
	void testJuan() throws Exception {
		mockMvc.perform(get("/juan"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_VALUE))
				.andExpect(content().string("Cao!"));
	}
}
