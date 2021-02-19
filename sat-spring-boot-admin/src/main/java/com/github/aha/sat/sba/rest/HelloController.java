package com.github.aha.sat.sba.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${hello.name:Arny}")
    private String name;

	@GetMapping("/hello")
	public String hello() {
        return String.format("Hello %s!", name);
    }

}
