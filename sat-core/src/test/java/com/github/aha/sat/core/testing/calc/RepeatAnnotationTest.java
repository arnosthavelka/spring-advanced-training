package com.github.aha.sat.core.testing.calc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;

@SpringBootTest(classes = { PackageConfig.class })
class RepeatAnnotationTest {

    private Random random = new Random();

    @Autowired
    private Calc calc;

    @Test
    @Repeat(10)
	void testRepeatAnnotation() {
        int a = random.nextInt(10);
        int b = random.nextInt(10);
        int result = calc.add(a, b);

        assertThat(result, equalTo(a + b));
    }
}
