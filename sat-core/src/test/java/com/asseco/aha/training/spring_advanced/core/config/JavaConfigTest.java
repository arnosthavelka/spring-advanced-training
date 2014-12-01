package com.asseco.aha.training.spring_advanced.core.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JavaConfigApplication.class)
public class JavaConfigTest {

    private Logger LOG = LoggerFactory.getLogger(JavaConfigTest.class);

    @Autowired
    @Qualifier("helloGreeting")
    private String helloBean;

    @Autowired
    @Qualifier("hiGreeting")
    private String hiBean;

    @Autowired
    @Qualifier("randomGreeting")
    private String randomBean;

    @Autowired
    @Qualifier("userArny")
    private User userArny;

    @Test
    public void testHello() {
        assertEquals("Hello all!", helloBean);
    }

    @Test
    public void testHi() {
        assertEquals("Hi all!", hiBean);
    }

    @Test
    public void testRandom() {
        LOG.info(randomBean.toString());
    }

    @Test
    public void testUserInstance() {
        assertEquals("Arny", userArny.getName());
    }
}
