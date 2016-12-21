package com.github.aha.sat.core.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.github.aha.sat.core.conditional.condition.AlcoholCondition;
import com.github.aha.sat.core.conditional.condition.SodaCondition;
import com.github.aha.sat.core.wiring.Drink;

@Configuration
@ComponentScan
public class ConditionalConfig {

	@Bean
	@Conditional(AlcoholCondition.class)
	public Drink beer()	{
		return new Drink() {
			@Override
			public String getName() {
				return "Beer";
			}
		};
	}

	@Bean
	@Conditional(AlcoholCondition.class)
	public Drink wine() {
		return new Drink() {
			@Override
			public String getName() {
				return "Wine";
			}
		};
	}

	@Bean
	@Conditional(SodaCondition.class)
	public Drink cola() {
		return new Drink() {
			@Override
			public String getName() {
				return "cola";
			}
		};
	}

	@Bean
	@Conditional(SodaCondition.class)
	public Drink fanta() {
		return new Drink() {
			@Override
			public String getName() {
				return "fanta";
			}
		};
	}

}
