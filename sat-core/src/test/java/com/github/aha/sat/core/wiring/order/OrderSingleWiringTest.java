package com.github.aha.sat.core.wiring.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;
import com.github.aha.sat.core.wiring.beverage.AbstractCarbonatedBeverage;
import com.github.aha.sat.core.wiring.beverage.Beer;
import com.github.aha.sat.core.wiring.beverage.Beverage;
import com.github.aha.sat.core.wiring.beverage.Cola;
import com.github.aha.sat.core.wiring.beverage.Soda;
import com.github.aha.sat.core.wiring.beverage.Tea;
import com.github.aha.sat.core.wiring.trait.Alcoholic;

@SpringBootTest(classes = WiringConfig.class)
public class OrderSingleWiringTest {

	@Autowired
	private BeverageOrder<Tea> teaOrder;

	@Autowired
	private BeverageOrder<? extends AbstractCarbonatedBeverage> sodaOrder;

	@Autowired
	@Qualifier("colaOrder")
	private BeverageOrder<? extends AbstractCarbonatedBeverage> beverageOrder;

	@Autowired
	private BeverageOrder<@Alcoholic ? extends AbstractCarbonatedBeverage> beerOrder;

	@Test
	void shouldWireBeanByType() {
		assertThat(teaOrder.takeOrder(new Tea())).isEqualTo("Tea order is taken.");
	}

	@Test
	void shouldWireBeanByName() {
		assertThat(sodaOrder.takeOrder(castToCarbonatedBeverage(new Soda()))).isEqualTo("Soda is ready to be served.");
	}

	@Test
	void shouldWireBeanByQualifier() {
		assertThat(beverageOrder.takeOrder(castToCarbonatedBeverage(new Cola()))).isEqualTo("Cola is temporarily not available.");
	}

	@Test
	void shouldWireBeanByAnnotation() {
		assertThat(beerOrder.takeOrder(castToCarbonatedBeverage(new Beer()))).isEqualTo("Waiting for a new keg ...");
	}

	<T extends AbstractCarbonatedBeverage> T castToCarbonatedBeverage(Beverage beer) {
		return (T) beer;
	}
}
