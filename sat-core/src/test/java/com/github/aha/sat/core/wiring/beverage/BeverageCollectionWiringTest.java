package com.github.aha.sat.core.wiring.beverage;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;
import com.github.aha.sat.core.wiring.beverage.AbstractCarbonatedBeverage;
import com.github.aha.sat.core.wiring.beverage.AbstractHotBeverage;
import com.github.aha.sat.core.wiring.beverage.Beverage;
import com.github.aha.sat.core.wiring.trait.Alcoholic;

@SpringBootTest(classes = WiringConfig.class)
class BeverageCollectionWiringTest {
	
	@Autowired
	private Collection<? extends Beverage> beverages;

	@Autowired
	private Collection<? extends AbstractCarbonatedBeverage> carbonatedBeverages;

	@Autowired
	private Collection<AbstractHotBeverage> hotBeverages;

	@Autowired
	@Alcoholic
	private Collection<Beverage> alcoholicBeverages;

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

	@Test
	void shouldWireAlcoholicBeverages() {
		assertThat(alcoholicBeverages).hasSize(1);
		assertThat(alcoholicBeverages).map(Beverage::getName).contains("Beer");
	}

}
