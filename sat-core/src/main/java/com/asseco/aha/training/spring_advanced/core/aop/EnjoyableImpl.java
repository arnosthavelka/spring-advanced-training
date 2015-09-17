package com.asseco.aha.training.spring_advanced.core.aop;

import com.asseco.aha.training.spring_advanced.core.wiring.Drink;

public class EnjoyableImpl implements Enjoyable {

	@Override
	public String enjoy(Drink drink) {
		return String.format("Wow %s!", drink.getName());
	}

}
