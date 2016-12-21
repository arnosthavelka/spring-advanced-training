package com.github.aha.sat.core.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.aha.sat.core.wiring.Drink;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Bean
	public Drink tea() {
		return new Drink() {

			@Override
			public String getName() {
				return "Tea";
			}

		};
    }

    @Bean
	public EnjoyableDrink beer() {
		return new EnjoyableDrink() {

			@Override
			public String getName() {
				return "Beer";
			}

		};
    }

}
