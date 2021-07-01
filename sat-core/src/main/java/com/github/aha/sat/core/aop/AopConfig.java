package com.github.aha.sat.core.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.aha.sat.core.wiring.Drink;

@Configuration
public class AopConfig {

    @Bean
	public Drink tea() {
		return () -> "Tea";
    }

    @Bean
	public EnjoyableDrink beer() {
		return () -> "Beer";
    }

}
