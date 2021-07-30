package com.github.aha.sat.core.wiring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.aha.sat.core.wiring.beverage.Beer;
import com.github.aha.sat.core.wiring.beverage.Beverage;
import com.github.aha.sat.core.wiring.beverage.Cola;
import com.github.aha.sat.core.wiring.beverage.Soda;
import com.github.aha.sat.core.wiring.beverage.Tea;
import com.github.aha.sat.core.wiring.order.BeverageOrder;

@SpringBootApplication
public class WiringConfig {

	@Bean
	public BeverageOrder<Beer> beerOrder() {
		return beverage -> "Waiting for a new keg ...";
	}

	@Bean
	public BeverageOrder<Cola> colaOrder() {
		return beverage -> beverage.getName() + " is temporarily not available.";
	}

	@Bean
	public Beverage iceTea() {
		return () -> "Ice Tea";
	}

	@Bean
	public BeverageOrder<Soda> sodaOrder() {
		return beverage -> beverage.getName() + " is ready to be served.";
	}

	@Bean
	public BeverageOrder<Tea> teaOrder() {
		return beverage -> beverage.getName() + " order is taken.";
	}

}
