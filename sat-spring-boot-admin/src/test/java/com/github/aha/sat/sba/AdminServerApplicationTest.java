package com.github.aha.sat.sba;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AdminServerApplication.class)
class AdminServerApplicationTest {

	@Test
	void contextLoads() {
		assertThatNoException();
	}

}

