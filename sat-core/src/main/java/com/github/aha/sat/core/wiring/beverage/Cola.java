package com.github.aha.sat.core.wiring.beverage;

import org.springframework.stereotype.Component;

@Component
public class Cola extends AbstractCarbonatedBeverage {

	@Override
	public String getName() {
		return "Cola";
	}

}
