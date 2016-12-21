package com.asseco.aha.training.spring_advanced.core.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import com.asseco.aha.training.spring_advanced.core.Controller.ControllerApplication;
import com.asseco.aha.training.spring_advanced.core.Controller.HelloController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ControllerApplication.class })
@WebAppConfiguration
public class HelloControllerTest {

	@Test
	public void test() throws Exception {
		HelloController controller = new HelloController();
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/jsp/hello.jsp")).build();

		mockMvc.perform(get("/hello/Arny"))
			.andExpect(view().name("hello"))
			.andExpect(model().attributeExists("name"))
			.andExpect(model().attribute("name", "Arny"));
	}
}
