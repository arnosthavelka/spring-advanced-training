package com.github.aha.sat.core.wiring.beverage;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.wiring.WiringConfig;
import com.github.aha.sat.core.wiring.trait.Alcoholic;

@SpringBootTest(classes = WiringConfig.class)
class BeverageSingleWiringTest {
	
	@Autowired
	private Beverage soda; // it's tea due to @Primary annotation

	@Autowired
	@Qualifier("soda")
	private Beverage qualifiedBeverage;

	@Autowired
	private AbstractCarbonatedBeverage cola;

	@Autowired
	@Alcoholic
	private Beverage coldBeer;

	@Test
	void shouldWirePrimaryBean() {
		assertThat(soda.getName()).isEqualTo("Tea");
	}

	@Test
	void shouldWireBeanByQualifier() {
		assertThat(qualifiedBeverage.getName()).isEqualTo("Soda");
	}

	@Test
	void shouldWireBeanByName() {
		assertThat(cola.getName()).isEqualTo("Cola");
	}

	@Test
	void shouldWireBeanByAnnotation() {
		assertThat(coldBeer.getName()).isEqualTo("Beer");
	}

}
