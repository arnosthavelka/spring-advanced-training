package com.github.aha.sat.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ScopeConfig.class)
class ProxyModeTest {

    @Autowired
    @Qualifier("beanSingleton")
    private TokenBean bean;

    @Test
    void contextLoads() {
        assertThat(bean.getToken()).isNotEqualTo(bean.getToken());
    }

}
