package com.github.aha.sat.core.wiring;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.trait.Alcoholic;

@SpringBootTest(classes = WiringConfig.class)
class MapWiringTest {
	
	@Autowired
	private Map<String, Beverage> beverages;

	@Autowired
	private Map<String, ? extends AbstractCarbonatedBeverage> carbonatedBeverages;

	@Autowired
	@Alcoholic
	private Map<String, Beverage> alcoholicBeverages;

	@Test
	void shouldWireAllBeverages() {
		assertThat(beverages)
				.hasSize(6)
				.containsKeys("beer", "cola", "soda", "coffee", "tea", "iceTea");
	}

	@Test
	void shouldWireCarbonatedBeverages() {
		assertThat(carbonatedBeverages)
				.hasSize(3)
				.containsKeys("beer", "cola", "soda");
	}

	@Test
	void shouldWireAlcoholicBeverages() {
		assertThat(alcoholicBeverages)
				.hasSize(1)
				.containsKey("beer");
		assertThat(alcoholicBeverages.get("beer").getName()).contains("Beer");
	}

}
