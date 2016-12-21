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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.asseco.aha.training.spring_advanced.core.wiring.Drink;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConditionalConfig.class)
public class AbstractConditionalTest {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractConditionalTest.class);

	@Autowired
	private List<Drink> drinks;

	@Test
	public void testDrinks() {
		assertThat(drinks, not(empty()));
		assertThat(drinks.size(), equalTo(2));
		printBeans();
	}

	private void printBeans() {
		drinks.stream().forEach(d -> LOG.info("bean {}", d.getName()));
	}

}
