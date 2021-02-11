package com.github.aha.sat.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JavaConfigApplication.class)
class JavaConfigTest {

    @Autowired
    @Qualifier("helloGreeting")
    private String helloBean;

    @Autowired
    @Qualifier("hiGreeting")
    private String hiBean;

    @Autowired
    @Qualifier("randomGreeting")
    private String randomBean;

    @Autowired
    @Qualifier("userArny")
    private User userArny;

    @Test
	void testHello() {
		assertThat(helloBean).isEqualTo("Hello all!");
    }

    @Test
	void testHi() {
		assertThat(hiBean).isEqualTo("Hi all!");
    }

    @Test
	void testRandom() {
		assertThat(randomBean).isNotEmpty();
    }

    @Test
	void testUserInstance() {
		assertThat(userArny.getName()).isEqualTo("Arny");
    }

}
