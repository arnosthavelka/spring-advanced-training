package com.github.aha.sat.core.wiring.order;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;
import com.github.aha.sat.core.wiring.beverage.AbstractCarbonatedBeverage;

@SpringBootTest(classes = WiringConfig.class)
class OrderMapWiringTest {
	
	@Autowired
	private Map<String, BeverageOrder<? extends AbstractCarbonatedBeverage>> allOrders;

	@Test
	void shouldWireAllOrders() {
		assertThat(allOrders)
				.hasSize(3)
				.containsKeys("beerOrder", "colaOrder", "sodaOrder");
	}

}
