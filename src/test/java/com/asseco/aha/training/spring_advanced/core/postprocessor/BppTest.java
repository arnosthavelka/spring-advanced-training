package com.asseco.aha.training.spring_advanced.core.postprocessor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.spring_advanced.core.postprocessor.PpApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PpApplication.class)
public class BppTest {

    @Autowired
    @Qualifier("hello")
    private String bean;

    @Test
    public void contextLoads() {
        Assert.assertEquals("HI ALL!", bean.toString());
    }
}
