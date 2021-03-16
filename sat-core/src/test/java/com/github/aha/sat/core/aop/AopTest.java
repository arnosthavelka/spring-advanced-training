package com.github.aha.sat.core.aop;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.Drink;

@SpringBootTest
class AopTest {

	@Autowired
	@Qualifier("tea")
	private Drink tea;

	@Autowired
	@Qualifier("beer")
	private Drink beer;

	@Test
	void testTea() {
		assertThat(tea.getName(), is(equalTo("Tea")));
	}

	@Test
	void testEnhancedBear() {
		assertThat(beer.getName(), equalTo("Beer"));
		if (beer instanceof Enjoyable) {
			Enjoyable en = (Enjoyable) beer;
			assertThat(en.enjoy(beer), equalTo("Wow Beer!"));
		}
    }

}
