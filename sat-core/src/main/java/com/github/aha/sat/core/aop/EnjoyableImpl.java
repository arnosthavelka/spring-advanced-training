package com.github.aha.sat.core.aop;

import com.github.aha.sat.core.wiring.Beverage;

public class EnjoyableImpl implements Enjoyable {

	@Override
	public String enjoy(Beverage drink) {
		return String.format("Wow %s!", drink.getName());
	}

}
