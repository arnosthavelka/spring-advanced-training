package com.asseco.aha.training.spring_advanced.core.wiring;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.spring_advanced.core.wiring.trait.Alcoholic;
import com.asseco.aha.training.spring_advanced.core.wiring.trait.NonAlcoholic;
import com.asseco.aha.training.spring_advanced.core.wiring.trait.Sparkling;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WiringConfig.class)
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
		assertThat(tea.getName(), is(equalTo("Tea")));
	}

	@Test
	public void testBeverage() {
		assertThat(beverage.getName(), is(equalTo("Cola")));
    }

	@Test
	public void testBeer() {
		assertThat(beer.getName(), is(equalTo("Beer")));
	}

}
