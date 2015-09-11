package com.asseco.aha.training.spring_advanced.core.testing.profile;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.spring_advanced.core.config.User;
import com.asseco.aha.training.spring_advanced.core.testing.profile.BarbaraConfig;
import com.asseco.aha.training.spring_advanced.core.testing.profile.ProfileConfig;
import com.asseco.aha.training.spring_advanced.core.testing.profile.RichardConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ProfileConfig.class, RichardConfig.class, BarbaraConfig.class })
public abstract class AbstractConfigTest {

    @Autowired
    @Qualifier("user")
    protected User user;

}
