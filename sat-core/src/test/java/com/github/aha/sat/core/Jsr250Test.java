package com.github.aha.sat.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.aha.sat.core.scope.TokenBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;

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
        TokenBean myBean() {
            return new TokenBean() {

                @PostConstruct
                void init() {
                    System.out.println("In init block of token");
                }

                @PreDestroy
                void destroy() {
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
