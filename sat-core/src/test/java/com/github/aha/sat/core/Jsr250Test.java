package com.github.aha.sat.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.aha.sat.core.scope.TokenBean;

@SpringBootTest
class Jsr250Test {

    @Resource
    private TokenBean bean;

    @Test
	void contextLoads() {
        assertThat(bean.getToken(), is(equalTo("token")));
    }

    @Configuration
	static class Context {

        @Bean
		public TokenBean myBean() {
            return new TokenBean() {

                @PostConstruct
				public void init() {
                    System.out.println("In init block of token");
                }

                @PreDestroy
				public void destroy() {
                    System.out.println("In destroy block of token");
                }

                @Override
				public String getToken() {
                    return "token";
                }
            };
        }
    }
}
