package com.github.aha.sat.core.testing.profile;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("barbara")
class BarbaraConfigTest extends AbstractConfigTest {

    @Test
	void testUserOK() {
        assertThat(user.getName(), equalTo("Barbara"));
        // for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
        // System.out.println(entry.getKey() + "/" + entry.getValue());
        // }

    }

    @Test
    @IfProfileValue(name = "test-group", values = { "training" })
	void testUserFailure() {
        assertThat(user.getName(), not(equalTo("Arny")));
    }
}
