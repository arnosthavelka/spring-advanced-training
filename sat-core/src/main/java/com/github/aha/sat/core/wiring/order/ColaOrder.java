package com.github.aha.sat.core.wiring.order;

import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.beverage.Cola;

@Component
public class ColaOrder implements BeverageOrder<Cola> {

	public String takeOrder(Cola beverage) {
		return beverage.getName() + " is temporarily not available.";
	}

}
