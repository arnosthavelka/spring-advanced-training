package com.github.aha.sat.core;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CoreApplication.class)
class CoreApplicationTests {

	@Test
	void contextLoads() {
		assertThatNoException();
	}

}
