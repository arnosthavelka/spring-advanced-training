package com.github.aha.sat.core.wiring.bean;

import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.AbstractCarbonatedBeverage;

@Component
public class Soda extends AbstractCarbonatedBeverage {

	@Override
	public String getName() {
		return "Soda";
	}

}
