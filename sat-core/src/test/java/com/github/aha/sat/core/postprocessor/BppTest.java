package com.github.aha.sat.core.postprocessor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PostProcessorConfig.class)
class BppTest {

    @Autowired
    @Qualifier("crown")
    private String bean;

    @Test
    void contextLoads() {
        assertThat(bean).hasToString("ONE WHO WEARS THE CROWN, BEARS THE CROWN.");
    }

}
