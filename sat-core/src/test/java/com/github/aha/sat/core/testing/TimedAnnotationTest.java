package com.github.aha.sat.core.testing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.aha.sat.core.testing.profile.PackageConfig;

@RunWith(SpringRunner.class)
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

    @Test(timeout = 100)
    public void testJUnitTimeout() throws InterruptedException {
        Thread.sleep(50);
        // Thread.sleep(120);
        assertThat(true, equalTo(Boolean.TRUE));
    }

}
