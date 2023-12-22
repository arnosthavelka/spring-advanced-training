package com.github.aha.sat.core.wiring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.aha.sat.core.wiring.beverage.Beverage;
import com.github.aha.sat.core.wiring.beverage.Soda;
import com.github.aha.sat.core.wiring.order.BeverageOrder;

@SpringBootApplication
public class WiringConfig {

    @Bean
    Beverage iceTea() {
		return () -> "Ice Tea";
	}

    @Bean
    BeverageOrder<Soda> sodaOrder() {
		return beverage -> beverage.getName() + " is ready to be served.";
	}

}
