package com.github.aha.sat.core.testing.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aha.sat.core.config.User;

@SpringBootTest(classes = { RichardConfig.class, BarbaraConfig.class })
abstract class AbstractConfigTest {

    @Autowired
    protected User user;

}
