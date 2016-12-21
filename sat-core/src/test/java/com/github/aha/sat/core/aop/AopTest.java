package com.github.aha.sat.core.aop;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.aha.sat.core.aop.AopApplication;
import com.github.aha.sat.core.aop.Enjoyable;
import com.github.aha.sat.core.wiring.Drink;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AopApplication.class)
public class AopTest {

	@Autowired
	@Qualifier("tea")
	private Drink tea;

	@Autowired
	@Qualifier("beer")
	private Drink beer;

	@Test
	public void testTea() {
		assertThat(tea.getName(), is(equalTo("Tea")));
	}

	@Test
	public void testEnhancedBear() {
		assertThat(beer.getName(), equalTo("Beer"));
		if (beer instanceof Enjoyable) {
			Enjoyable en = (Enjoyable) beer;
			assertThat(en.enjoy(beer), equalTo("Wow Beer!"));
		}
    }

}
