package com.github.aha.sat.core.aop;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.Drink;

@SpringBootTest(classes = AopConfig.class)
class AopTest {

	@Autowired
	@Qualifier("tea")
	private Drink tea;

	@Autowired
	@Qualifier("coffee")
	private Drink coffee;

	@Test
	void testTea() {
		assertThat(tea.getName(), is(equalTo("Tea")));
	}

	@Test
	void testEnhancedBear() {
		assertThat(coffee.getName(), equalTo("Coffee"));
		if (coffee instanceof Enjoyable) {
			Enjoyable en = (Enjoyable) coffee;
			assertThat(en.enjoy(coffee), equalTo("Wow Coffee!"));
		}
    }

}
