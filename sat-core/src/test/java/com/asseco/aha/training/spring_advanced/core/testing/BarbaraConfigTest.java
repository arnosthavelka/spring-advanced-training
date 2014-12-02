package com.asseco.aha.training.spring_advanced.core.testing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("barbara")
public class BarbaraConfigTest extends MyConfigTest {

    @Test
    public void testHello() {
        assertThat(user.getName(), equalTo("Barbara"));
    }
}
