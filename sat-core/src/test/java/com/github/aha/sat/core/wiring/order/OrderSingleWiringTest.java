package com.github.aha.sat.core.wiring.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;
import com.github.aha.sat.core.wiring.beverage.AbstractCarbonatedBeverage;
import com.github.aha.sat.core.wiring.beverage.Beer;
import com.github.aha.sat.core.wiring.beverage.Tea;

@SpringBootTest(classes = WiringConfig.class)
public class OrderSingleWiringTest {

	@Autowired
	private BeverageOrder<Tea> teaOrder;

	@Autowired
	private BeverageOrder<? extends AbstractCarbonatedBeverage> beerOrder;

	@Test
	void shouldWireBeanByType() {
		assertThat(teaOrder.takeOrder(new Tea())).isEqualTo("Tea order is taken.");
	}

	@Test
	void shouldWireBeanByName() {
		AbstractCarbonatedBeverage beer = new Beer();
		// FIXME pass beverage to the order
		assertThat(beerOrder.takeOrder(null)).isEqualTo("Waiting for a new keg ...");
	}

}
