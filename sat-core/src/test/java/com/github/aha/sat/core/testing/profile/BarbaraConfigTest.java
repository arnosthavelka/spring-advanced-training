package com.github.aha.sat.core.testing.profile;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("barbara")
class BarbaraConfigTest extends AbstractConfigTest {

    @Test
	void testUserOK() {
		assertThat(user.getName()).isEqualTo("Barbara");
    }

    @Test
    @IfProfileValue(name = "test-group", values = { "training" })
	void testUserFailure() {
		assertThat(user.getName()).isNotEqualTo("Arny");
    }

}
