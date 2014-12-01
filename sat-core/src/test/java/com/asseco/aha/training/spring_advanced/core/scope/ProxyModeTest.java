package com.asseco.aha.training.spring_advanced.core.scope;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ScopeApplication.class)
public class ProxyModeTest {

    @Autowired
    @Qualifier("beanSingleton")
    private TokenBean bean;

    @Test
    public void contextLoads() {
        assertThat(bean.getToken(), is(not(bean.getToken())));
        // Assert.assertNotEquals(bean.getToken(), bean.getToken());
    }
}
