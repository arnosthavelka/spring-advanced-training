package com.github.aha.sat.core.conditional;

import org.junit.jupiter.api.BeforeAll;

public class SodaConditionalTest extends AbstractConditionalTest {

	@BeforeAll
	public static void init() {
		System.setProperty("soda", "aha");
	}
}
