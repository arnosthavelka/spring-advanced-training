package com.asseco.aha.training.spring_advanced.core.wiring.bean;

import org.springframework.stereotype.Component;

import com.asseco.aha.training.spring_advanced.core.wiring.Drink;
import com.asseco.aha.training.spring_advanced.core.wiring.trait.Alcoholic;
import com.asseco.aha.training.spring_advanced.core.wiring.trait.Sparkling;

@Component
@Alcoholic
@Sparkling
public class Beer implements Drink {

	@Override
	public String getName() {
		return "Beer";
	}

}
