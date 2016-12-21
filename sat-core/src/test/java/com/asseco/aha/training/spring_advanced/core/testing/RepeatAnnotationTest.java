package com.asseco.aha.training.spring_advanced.core.testing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import com.asseco.aha.training.spring_advanced.core.testing.profile.PackageConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PackageConfig.class })
public class RepeatAnnotationTest {

    private Random random = new Random();

    @Autowired
    private Calc calc;

    @Test
    @Repeat(10)
    public void testRepeatAnnotation() {
        int a = random.nextInt(10);
        int b = random.nextInt(10);
        int result = calc.add(a, b);

        assertThat(result, equalTo(a + b));
    }
}
