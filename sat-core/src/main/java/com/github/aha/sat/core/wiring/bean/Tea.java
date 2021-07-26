package com.github.aha.sat.core.wiring.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.AbstractHotBeverage;

@Component
@Primary
public class Tea extends AbstractHotBeverage {

	@Override
	public String getName() {
		return "Tea";
	}

}
