package com.asseco.aha.training.spring_advanced.core.conditional;

import org.junit.BeforeClass;

public class AlcoholicConditionalTest extends AbstractConditionalTest {

	@BeforeClass
	public static void init() {
		System.setProperty("alcohol", "aha");
	}
}
