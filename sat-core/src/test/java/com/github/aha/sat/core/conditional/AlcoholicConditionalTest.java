package com.github.aha.sat.core.conditional;

import org.junit.jupiter.api.BeforeAll;

public class AlcoholicConditionalTest extends AbstractConditionalTest {

	@BeforeAll
	public static void init() {
		System.setProperty("alcohol", "aha");
	}
}
