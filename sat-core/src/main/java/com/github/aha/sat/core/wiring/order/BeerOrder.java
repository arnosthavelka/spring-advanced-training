package com.github.aha.sat.core.wiring.order;

import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.beverage.Beer;

@Component
public class BeerOrder implements BeverageOrder<Beer> {

	public String takeOrder(Beer beverage) {
		return "Waiting for a new keg ...";
	}

}
