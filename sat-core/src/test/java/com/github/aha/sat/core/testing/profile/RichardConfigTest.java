package com.github.aha.sat.core.testing.profile;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("richard")
public class RichardConfigTest extends AbstractConfigTest {

    @Test
    public void testUser() {
        assertThat(user.getName(), equalTo("Richard"));
    }
}
