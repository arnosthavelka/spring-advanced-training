package com.github.aha.sat.core.wiring.beverage;

import org.springframework.stereotype.Component;

@Component
public class Coffee extends AbstractHotBeverage {

	@Override
	public String getName() {
		return "Coffee";
	}

}
