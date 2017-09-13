package com.github.aha.sat.core.postprocessor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PostProcessorApplication.class)
public class BppTest {

    @Autowired
    @Qualifier("hello")
    private String bean;

    @Test
    public void contextLoads() {
        assertThat(bean.toString(), is(equalTo("HI ALL!")));
        // Assert.assertEquals("HI ALL!", bean.toString());
    }
}
