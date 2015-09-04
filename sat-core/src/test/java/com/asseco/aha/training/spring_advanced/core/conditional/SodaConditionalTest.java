package com.asseco.aha.training.spring_advanced.core.conditional;

import org.junit.BeforeClass;

public class SodaConditionalTest extends AbstractConditionalTest {

	@BeforeClass
	public static void init() {
		System.setProperty("soda", "aha");
	}
}
