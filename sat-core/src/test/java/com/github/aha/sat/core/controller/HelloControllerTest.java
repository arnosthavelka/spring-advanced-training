package com.github.aha.sat.core.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

@SpringBootTest(classes = { ControllerApplication.class })
@WebAppConfiguration
class HelloControllerTest {

	@Test
	void test() throws Exception {
		HelloController controller = new HelloController();
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/jsp/hello.jsp")).build();

		mockMvc.perform(get("/hello/Arny"))
			.andExpect(view().name("hello"))
			.andExpect(model().attributeExists("name"))
			.andExpect(model().attribute("name", "Arny"));
	}
}
