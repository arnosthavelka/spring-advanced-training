package com.github.aha.sat.core.conditional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.aha.sat.core.wiring.Beverage;

abstract class AbstractConditionalTest {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractConditionalTest.class);

	@Autowired
	private List<Beverage> drinks;

	@Test
	void testDrinks() {
		assertThat(drinks, not(empty()));
		assertThat(drinks.size(), equalTo(2));
		printBeans();
	}

	private void printBeans() {
		drinks.stream().forEach(d -> LOG.info("bean {}", d.getName()));
	}

}
