package com.github.aha.sat.core.config;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.aha.sat.core.testing",
		excludeFilters = @ComponentScan.Filter(type = ANNOTATION, value = Component.class))
public class TestingConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingConfigApplication.class, args);
	}

	@Bean
	String hi() {
		return "Hi all!";
	}

	@Bean
	String hello() {
		return "Hello all!";
	}

	@Bean
	String randomGreeting() {
		int val = new SecureRandom().nextInt(10) % 2;
		return val == 0 ? hi() : hello();
	}

	@Bean
	String hiGreeting(String hi) {
		return hi;
	}

	@Bean
	String helloGreeting(@Qualifier("hello") String bean) {
		return bean;
	}

	@Bean
	User userArny() {
		return new User("Arny");
	}

}
