package com.asseco.aha.training.spring_advanced.core.wiring.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.asseco.aha.training.spring_advanced.core.wiring.Drink;

@Component
@Primary
public class Tea implements Drink {

	@Override
	public String getName() {
		return "Tea";
	}

}
