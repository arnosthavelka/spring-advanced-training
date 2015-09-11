package com.asseco.aha.training.spring_advanced.core.testing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.spring_advanced.core.testing.profile.PackageConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { PackageConfig.class })
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
