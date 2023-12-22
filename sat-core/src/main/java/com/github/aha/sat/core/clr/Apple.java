package com.github.aha.sat.core.clr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Order(1)
public class Apple implements CommandLineRunner {

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(Apple.class);

	@PostConstruct
	void init() {
		LOG.info("Initializing Apple bean ...");
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Here's Apple runner");
	}

}
