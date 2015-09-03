package com.asseco.aha.training.spring_advanced.core.wiring.bean;

import org.springframework.stereotype.Component;

import com.asseco.aha.training.spring_advanced.core.wiring.Drink;
import com.asseco.aha.training.spring_advanced.core.wiring.trait.NonAlcoholic;
import com.asseco.aha.training.spring_advanced.core.wiring.trait.Sparkling;

@Component
@NonAlcoholic
@Sparkling
public class Cola implements Drink {

	@Override
	public String getName() {
		return "Cola";
	}

}
