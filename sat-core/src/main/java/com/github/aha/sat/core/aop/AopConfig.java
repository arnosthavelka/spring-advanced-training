package com.github.aha.sat.core.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.aha.sat.core.wiring.beverage.Beverage;

@Configuration
public class AopConfig {

    @Bean
	public Beverage tea() {
		return () -> "Tea";
    }

    @Bean
	public EnjoyableBeverage coffee() {
		return () -> "Coffee";
    }

}
