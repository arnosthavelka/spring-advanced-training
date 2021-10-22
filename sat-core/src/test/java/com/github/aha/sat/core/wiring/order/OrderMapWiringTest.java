package com.github.aha.sat.core.wiring.order;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;

@SpringBootTest(classes = WiringConfig.class)
class OrderMapWiringTest {
	
	@Autowired
	private Map<String, BeverageOrder<?>> allOrders;

	@Test
	void shouldWireAllOrders() {
		assertThat(allOrders)
				.hasSize(4)
				.containsKeys("beerOrder", "colaOrder", "teaOrder", "sodaOrder");
	}

}
