package com.github.aha.sat.core.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
	public String sayHello(@PathVariable String name) {
		return data.get(name.toLowerCase());
	}

}
