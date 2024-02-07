package com.github.aha.sat.core.testing.calc;

import static java.lang.Boolean.TRUE;
import static java.time.Duration.ofMillis;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Timed;

@SpringBootTest(classes = { PackageConfig.class })
class TimedAnnotationTest {

    @Test
    @Timed(millis = 100)
    void testOkTime() {
        assertThat(true, equalTo(TRUE));
    }

    @Test()
    @Timed(millis = 100)
    void testSpringTimeout() throws InterruptedException {
        Thread.sleep(50); // NOSONAR
        assertThat(true, equalTo(TRUE));
    }

    @Test
    void testJUnitTimeout() throws InterruptedException {
        assertTimeout(ofMillis(100), () -> {
            Thread.sleep(50); // NOSONAR
            assertThat(true, equalTo(TRUE));
        });
    }

}
