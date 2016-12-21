package com.github.aha.sat.core.testing.profile;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.aha.sat.core.config.User;
import com.github.aha.sat.core.testing.profile.BarbaraConfig;
import com.github.aha.sat.core.testing.profile.ProfileConfig;
import com.github.aha.sat.core.testing.profile.RichardConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ProfileConfig.class, RichardConfig.class, BarbaraConfig.class })
public abstract class AbstractConfigTest {

    @Autowired
    @Qualifier("user")
    protected User user;

}
