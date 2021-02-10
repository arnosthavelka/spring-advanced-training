package com.github.aha.sat.core.testing.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.config.User;

@SpringBootTest(classes = { ProfileConfig.class, RichardConfig.class, BarbaraConfig.class })
abstract class AbstractConfigTest {

    @Autowired
    @Qualifier("user")
    protected User user;

}
