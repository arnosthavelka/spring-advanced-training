package com.github.aha.sat.core.aop;

import com.github.aha.sat.core.wiring.Drink;

public class EnjoyableImpl implements Enjoyable {

	@Override
	public String enjoy(Drink drink) {
		return String.format("Wow %s!", drink.getName());
	}

}
