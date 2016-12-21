package com.github.aha.sat.core.clr;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Hi implements CommandLineRunner {

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(Hi.class);

	@PostConstruct
	void init() {
		LOG.info("Initializing Hi bean ...");
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Hi!");
	}

}
