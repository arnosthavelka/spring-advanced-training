package com.github.aha.sat.core.wiring.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;
import com.github.aha.sat.core.wiring.beverage.AbstractCarbonatedBeverage;
import com.github.aha.sat.core.wiring.beverage.Beer;
import com.github.aha.sat.core.wiring.beverage.Cola;
import com.github.aha.sat.core.wiring.beverage.Soda;

@SpringBootTest(classes = WiringConfig.class)
class OrderCollectionWiringTest {

	/**
	 * The exact name cannot be predicted as it's derived from lambda.
	 */
	private static final String SODA_ORDER = "WiringConfig$$Lambda";

	@Autowired
	private Collection<BeverageOrder<?>> allOrders;

	@Autowired
	private BeverageOrder<? extends AbstractCarbonatedBeverage>[] carbonatedOrders;

	@Autowired
	private BeverageOrder<Beer> beerOrder;

	@Autowired
	private BeverageOrder<Cola> colaOrder;

	@Autowired
	private BeverageOrder<Soda> sodaOrder;

	@Test
	void shouldWireAllOrders() {
		assertThat(allOrders).hasSize(4)
			.map(BeverageOrder::getClass)
			.map(Class::getSimpleName)
			.contains("BeerOrder", "ColaOrder", "TeaOrder")
			.anyMatch(className -> className.startsWith(SODA_ORDER));
	}

	@Test
	void shouldWireAllCarnonatedOrders() {
		assertThat(carbonatedOrders).contains(beerOrder, colaOrder, sodaOrder)
			.doesNotContain(beverage -> "Just dummy order");
	}

}
