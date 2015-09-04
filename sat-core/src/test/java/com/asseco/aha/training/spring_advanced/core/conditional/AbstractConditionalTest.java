package com.asseco.aha.training.spring_advanced.core.conditional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.spring_advanced.core.wiring.Drink;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConditionalConfig.class)
public class AbstractConditionalTest {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractConditionalTest.class);

	@Autowired
	private List<Drink> drinks;

	@Test
	public void listAllDrinks() {
		assertThat(drinks, not(empty()));
		assertThat(drinks.size(), equalTo(2));
		listAllBeans();
	}

	private void listAllBeans() {
		for (Drink drink : drinks) {
			LOG.info("bean {}", drink.getName());
		}

	}

}
