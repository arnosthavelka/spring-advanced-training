package com.asseco.aha.training.spring_advanced.core.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations = "classpath:spring/xml-config.xml")
public class XmlDirectConfigTest {

    @Autowired
    @Qualifier("user")
    private User user;

    @Test
    public void testHello() {
        assertThat(user.getName(), equalTo("Arny"));
    }

}
