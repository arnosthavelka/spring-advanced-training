package com.asseco.aha.training.spring_advanced.core.aop;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asseco.aha.training.spring_advanced.core.wiring.Drink;
import com.asseco.aha.training.spring_advanced.core.wiring.trait.Alcoholic;
import com.asseco.aha.training.spring_advanced.core.wiring.trait.Sparkling;

@RestController
public class TestController {

	@Autowired
	private Drink tea;

	@Autowired
	@Alcoholic
	@Sparkling
	private Drink beer;

	@RequestMapping("/drink")
	public String test() {
		int val = new Random().nextInt(2);
		return val == 1 ? tea.getName() : beer.getName();
	}

}
