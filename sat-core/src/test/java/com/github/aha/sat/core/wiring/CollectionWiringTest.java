package com.github.aha.sat.core.wiring;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WiringConfig.class)
class CollectionWiringTest {
	
	@Autowired
	private Collection<? extends Beverage> beverages;

	@Autowired
	private Collection<? extends AbstractCarbonatedBeverage> carbonatedBeverages;

	@Autowired
	private Collection<AbstractHotBeverage> hotBeverages;

	@Test
	void shouldWireAllBeverages() {
		assertThat(beverages).hasSize(6);
		assertThat(beverages).map(Beverage::getName).contains("Beer", "Cola", "Soda", "Coffee", "Tea", "Ice Tea");
	}

	@Test
	void shouldWireCarbonatedBeverages() {
		assertThat(carbonatedBeverages).hasSize(3);
		assertThat(carbonatedBeverages).map(Beverage::getName).contains("Beer", "Cola", "Soda");
	}

	@Test
	void shouldWireHotBeverages() {
		assertThat(hotBeverages).hasSize(2);
		assertThat(hotBeverages).map(Beverage::getName).contains("Coffee", "Tea");
	}

}
