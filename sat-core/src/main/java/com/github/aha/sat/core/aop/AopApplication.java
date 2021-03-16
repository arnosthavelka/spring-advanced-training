package com.github.aha.sat.core.aop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.aha.sat.core.wiring.Drink;

@SpringBootApplication
public class AopApplication {

    @Bean
	public Drink tea() {
		return () -> "Tea";
    }

    @Bean
	public EnjoyableDrink beer() {
		return () -> "Beer";
    }

}
