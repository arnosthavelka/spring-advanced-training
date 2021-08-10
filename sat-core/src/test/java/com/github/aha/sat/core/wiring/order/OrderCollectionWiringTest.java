package com.github.aha.sat.core.wiring.order;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;
import com.github.aha.sat.core.wiring.beverage.AbstractCarbonatedBeverage;

@SpringBootTest(classes = WiringConfig.class)
class OrderCollectionWiringTest {
	
	/**
	 * The exact name cannot be predicted as it's driven by lambda.
	 */
	private static final String SODA_ORDER = "WiringConfig$$Lambda$";

	@Autowired
	private Collection<BeverageOrder<?>> orders;

	@Autowired
	private Collection<BeverageOrder<? extends AbstractCarbonatedBeverage>> carnonatedOrders;

	@Test
	void shouldWireAllOrders() {
		assertThat(orders).hasSize(4);
		assertThat(orders).map(BeverageOrder::getClass).map(Class::getSimpleName)
				.contains("BeerOrder", "ColaOrder", "TeaOrder")
				.anyMatch(c -> c.startsWith(SODA_ORDER));
	}

	@Test
	void shouldWireAllCarnonatedOrders() {
		assertThat(carnonatedOrders).hasSize(3);
		assertThat(carnonatedOrders).map(BeverageOrder::getClass).map(Class::getSimpleName)
				.contains("BeerOrder", "ColaOrder")
				.anyMatch(c -> c.startsWith(SODA_ORDER));
	}

}
