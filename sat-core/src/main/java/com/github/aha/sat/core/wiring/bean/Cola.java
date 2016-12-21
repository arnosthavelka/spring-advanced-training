package com.github.aha.sat.core.wiring.bean;

import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.Drink;
import com.github.aha.sat.core.wiring.trait.NonAlcoholic;
import com.github.aha.sat.core.wiring.trait.Sparkling;

@Component
@NonAlcoholic
@Sparkling
public class Cola implements Drink {

	@Override
	public String getName() {
		return "Cola";
	}

}
