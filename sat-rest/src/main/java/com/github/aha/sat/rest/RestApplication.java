package com.github.aha.sat.rest;

import static org.springframework.boot.Banner.Mode.OFF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
		var app = new SpringApplication(RestApplication.class);
		app.setBannerMode(OFF);
        app.run(args);
    }

}
