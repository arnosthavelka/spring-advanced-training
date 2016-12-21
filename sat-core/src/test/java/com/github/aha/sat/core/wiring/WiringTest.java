package com.github.aha.sat.core.wiring;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.aha.sat.core.wiring.Drink;
import com.github.aha.sat.core.wiring.WiringConfig;
import com.github.aha.sat.core.wiring.trait.Alcoholic;
import com.github.aha.sat.core.wiring.trait.NonAlcoholic;
import com.github.aha.sat.core.wiring.trait.Sparkling;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WiringConfig.class)
public class WiringTest {

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
	public void testPrimaryBean() {
		assertThat(tea.getName(), equalTo("Tea"));
	}

	@Test
	public void testBeverage() {
		assertThat(beverage.getName(), equalTo("Cola"));
    }

	@Test
	public void testBeer() {
		assertThat(beer.getName(), equalTo("Beer"));
	}

}
