package com.github.aha.sat.core.testing.profile;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("richard")
class RichardConfigTest extends AbstractConfigTest {

	@Test
	void testUser() {
		assertThat(user.getName()).isEqualTo("Richard");
	}

}
