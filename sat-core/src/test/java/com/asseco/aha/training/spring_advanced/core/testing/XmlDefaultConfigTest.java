package com.asseco.aha.training.spring_advanced.core.testing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.spring_advanced.core.config.User;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class XmlDefaultConfigTest {

    @Autowired
    @Qualifier("user")
    private User user;

    @Test
    public void testHello() {
        assertThat(user.getName(), equalTo("Michel"));
    }

}
