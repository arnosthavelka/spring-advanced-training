package com.asseco.aha.training.spring_advanced.core.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController implements CommandLineRunner {

	private Map<String, String> data;

	@Override
	public void run(String... args) throws Exception {
		data = new HashMap<>();
		data.put("arny", "Hello!");
		data.put("bruno", "Hey man!");
		data.put("juan", "Cao!");
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String sayHello(@PathVariable String name) {
		return data.get(name.toLowerCase());
	}

}
