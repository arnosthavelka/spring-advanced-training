package com.github.aha.sat.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
class ApplicationTests {

	@Test
	void contextLoads() {
		assertThat(true).isTrue();
	}

}
