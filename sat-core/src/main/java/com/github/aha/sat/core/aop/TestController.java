package com.github.aha.sat.core.aop;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.aha.sat.core.wiring.Drink;

@RestController
public class TestController {

	@Autowired
	@Qualifier("tea")
	private Drink tea;

	@Autowired
	@Qualifier("beer")
	private Drink beer;

	@GetMapping("/drink")
	public String test() {
		var val = new SecureRandom().nextInt(2);
		return val == 1 ? getName(tea) : getName(beer);
	}

	private String getName(Drink drink) {
		if (drink instanceof Enjoyable) {
			Enjoyable en = (Enjoyable) drink;
			return en.enjoy(drink);
		}
		
		return drink.getName();
	}

}
