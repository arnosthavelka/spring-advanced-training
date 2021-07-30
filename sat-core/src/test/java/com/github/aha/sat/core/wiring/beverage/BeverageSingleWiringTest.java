package com.github.aha.sat.core.wiring.beverage;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;
import com.github.aha.sat.core.wiring.trait.Alcoholic;

@SpringBootTest(classes = WiringConfig.class)
class BeverageSingleWiringTest {
	
	@Autowired
	private Beverage primaryBeverage;

	@Autowired
	@Qualifier("iceTea")
	private Beverage quilifiedBeverage;

	@Autowired
	private AbstractCarbonatedBeverage cola;

	@Autowired
	@Alcoholic
	private Beverage beer;

	@Test
	void shouldWirePrimaryBean() {
		assertThat(primaryBeverage.getName()).isEqualTo("Tea");
	}

	@Test
	void shouldWireBeanByQualifier() {
		assertThat(quilifiedBeverage.getName()).isEqualTo("Ice Tea");
	}

	@Test
	void shouldWireBeanByName() {
		assertThat(cola.getName()).isEqualTo("Cola");
	}

	@Test
	void shouldWireBeanByAnnotation() {
		assertThat(beer.getName()).isEqualTo("Beer");
	}

}
