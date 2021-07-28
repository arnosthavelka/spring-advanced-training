package com.github.aha.sat.core.wiring.bean;

import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.AbstractHotBeverage;

@Component
public class Coffee extends AbstractHotBeverage {

	@Override
	public String getName() {
		return "Coffee";
	}

}
