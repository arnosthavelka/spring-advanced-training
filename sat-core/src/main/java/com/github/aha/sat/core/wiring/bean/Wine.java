package com.github.aha.sat.core.wiring.bean;

import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.Drink;
import com.github.aha.sat.core.wiring.trait.Alcoholic;
import com.github.aha.sat.core.wiring.trait.Sparkling;

@Component
@Alcoholic
@Sparkling
public class Wine implements Drink {

	@Override
	public String getName() {
		return "Wine";
	}

}
