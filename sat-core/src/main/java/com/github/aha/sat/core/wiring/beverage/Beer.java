package com.github.aha.sat.core.wiring.beverage;

import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.trait.Alcoholic;

@Component
@Alcoholic
public class Beer extends AbstractCarbonatedBeverage {

	@Override
	public String getName() {
		return "Beer";
	}

}
