package com.github.aha.sat.core.wiring.order;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = WiringConfig.class)
@Slf4j
class OrderCollectionWiringTest {
	
	@Autowired
	private Collection<BeverageOrder<?>> orders;

	@Test
	void shouldWireAllOrders() {
		orders.forEach(o -> log.info(o.getClass().getSimpleName()));
		assertThat(orders).hasSize(4);
		assertThat(orders).map(BeverageOrder::getClass).map(Class::getSimpleName).contains("BeerOrder", "ColaOrder", "TeaOrder");
	}

}
