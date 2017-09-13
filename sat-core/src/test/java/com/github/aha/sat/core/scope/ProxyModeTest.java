package com.github.aha.sat.core.scope;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ScopeApplication.class)
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
