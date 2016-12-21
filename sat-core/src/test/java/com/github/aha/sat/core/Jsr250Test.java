package com.github.aha.sat.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.aha.sat.core.scope.TokenBean;

@RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(classes = Jsr250Test.Context.class)
@ContextConfiguration
// source: http://www.java2blog.com/2012/09/spring-jsr-250-annotations.html
public class Jsr250Test {

    @Resource
    private TokenBean bean;

    @Test
    public void contextLoads() {
        assertThat(bean.getToken(), is(equalTo("token")));
    }

    @Configuration
    public static class Context {

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
