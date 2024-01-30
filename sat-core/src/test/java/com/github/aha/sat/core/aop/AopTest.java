package com.github.aha.sat.core.aop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.beverage.Beverage;

@SpringBootTest(classes = AopConfig.class)
class AopTest {

	@Autowired
	@Qualifier("milk")
	private Beverage milk;

	@Autowired
	@Qualifier("hotChocolade")
	private Beverage hotChocolade;

	@Test
	void testTea() {
		assertThat(milk.getName()).isEqualTo("Milk");
	}

	@Test
	void testEnhancedBear() {
		assertThat(hotChocolade.getName()).isEqualTo("Hot Chocolade");
		if (hotChocolade instanceof Enjoyable) {
			Enjoyable en = (Enjoyable) hotChocolade;
			assertThat(en.enjoy(hotChocolade)).isEqualTo("Wow Hot Chocolade");
		}
	}

}
