package com.asseco.aha.training.spring_advanced.core.postprocessor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PostProcessorApplication.class)
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
