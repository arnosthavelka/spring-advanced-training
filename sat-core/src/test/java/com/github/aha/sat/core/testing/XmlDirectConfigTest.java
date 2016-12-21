package com.github.aha.sat.core.testing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.aha.sat.core.config.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations="classpath:spring/xml-config.xml")
public class XmlDirectConfigTest {

    @Autowired
    @Qualifier("user")
    private User user;

    @Test
    public void testHello() {
        assertThat(user.getName(), equalTo("Arny"));
    }

}
