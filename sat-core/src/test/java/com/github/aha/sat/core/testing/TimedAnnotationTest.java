package com.github.aha.sat.core.testing;

import static java.time.Duration.ofMillis;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Timed;

import com.github.aha.sat.core.testing.profile.PackageConfig;

@SpringBootTest(classes = { PackageConfig.class })
public class TimedAnnotationTest {

    @Test
    @Timed(millis = 100)
    public void testOkTime() {
        assertThat(true, equalTo(Boolean.TRUE));
    }

    @Test()
    @Timed(millis = 100)
    public void testSpringTimeout() throws InterruptedException {
        Thread.sleep(50);
        // Thread.sleep(120);
        assertThat(true, equalTo(Boolean.TRUE));
    }

	@Test
    public void testJUnitTimeout() throws InterruptedException {
		assertTimeout(ofMillis(100), () -> {
			Thread.sleep(50);
			// Thread.sleep(120);
			assertThat(true, equalTo(Boolean.TRUE));
		});
    }

}
