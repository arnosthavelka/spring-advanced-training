package com.github.aha.sat.core.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JavaConfigApplication.class)
class JavaConfigTest {

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
	void testHello() {
        assertThat(helloBean, equalTo("Hello all!"));
        // assertEquals("Hello all!", helloBean);
    }

    @Test
	void testHi() {
        assertThat(hiBean, is(equalTo("Hi all!")));
    }

    @Test
	void testRandom() {
        LOG.info(randomBean.toString());
    }

    @Test
	void testUserInstance() {
        assertThat(userArny.getName(), is(equalTo("Arny")));
    }
}
