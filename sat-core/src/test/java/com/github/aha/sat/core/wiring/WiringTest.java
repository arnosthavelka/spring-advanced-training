package com.github.aha.sat.core.wiring;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.trait.Alcoholic;
import com.github.aha.sat.core.wiring.trait.NonAlcoholic;
import com.github.aha.sat.core.wiring.trait.Sparkling;

@SpringBootTest(classes = WiringConfig.class)
class WiringTest {

	@Autowired
	private Drink tea;

	@Autowired
	@NonAlcoholic
	private Drink beverage;

	@Autowired
	@Alcoholic
	@Sparkling
	private Drink beer;

	@Test
	void testPrimaryBean() {
		assertThat(tea.getName(), equalTo("Tea"));
	}

	@Test
	void testBeverage() {
		assertThat(beverage.getName(), equalTo("Cola"));
    }

	@Test
	void testBeer() {
		assertThat(beer.getName(), equalTo("Beer"));
	}

}
