package com.github.aha.sat.core.testing.profile;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("richard")
class RichardConfigTest extends AbstractConfigTest {

    @Test
	void testUser() {
        assertThat(user.getName(), equalTo("Richard"));
    }
}
