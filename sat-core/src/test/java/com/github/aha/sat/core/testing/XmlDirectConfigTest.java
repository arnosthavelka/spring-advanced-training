package com.github.aha.sat.core.testing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.aha.sat.core.config.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring/xml-config.xml")
class XmlDirectConfigTest {

    @Autowired
    @Qualifier("user")
    private User user;

    @Test
    void testHello() {
        assertThat(user.getName(), equalTo("Arny"));
    }

}
