package com.github.aha.sat.core.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
public class Apple implements CommandLineRunner {

	@PostConstruct
	void init() {
		log.debug("Initializing Apple bean ...");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Here's Apple runner");
	}

}
