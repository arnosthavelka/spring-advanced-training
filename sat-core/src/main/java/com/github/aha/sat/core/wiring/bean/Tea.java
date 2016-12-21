package com.github.aha.sat.core.wiring.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.github.aha.sat.core.wiring.Drink;

@Component
@Primary
public class Tea implements Drink {

	@Override
	public String getName() {
		return "Tea";
	}

}
