package com.asseco.aha.training.spring_advanced.core.testing;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.spring_advanced.core.config.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ProfileConfig.class, RichardConfig.class, BarbaraConfig.class })
public abstract class MyConfigTest {

    @Autowired
    @Qualifier("user")
    protected User user;

}
