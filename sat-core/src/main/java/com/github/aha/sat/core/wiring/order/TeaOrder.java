package com.github.aha.sat.core.wiring.order;

import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.beverage.Tea;

@Component
public class TeaOrder implements BeverageOrder<Tea> {

	public String takeOrder(Tea beverage) {
		return beverage.getName() + " order is taken.";
	}

}
