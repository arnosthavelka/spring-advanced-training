package com.asseco.aha.training.spring_advanced.core.scope;

import java.util.UUID;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.asseco.aha.training.spring_advanced.core.postprocessor.LoggerBfpp;

@Configuration
@ComponentScan("com.asseco.aha.training.spring_advanced.core")
@EnableAutoConfiguration
public class ProxyModeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyModeApplication.class, args);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
    public TokenBean beanPrototype() {
        return new TokenBean() {
            @Override
            public String getToken() {
                return UUID.randomUUID().toString();
            }
        };
    }

    @Bean
    public TokenBean beanSingleton(final TokenBean beanPrototype) {
        // public TokenBean beanSingleton() {
        return new TokenBean() {
            @Override
            public String getToken() {
                return beanPrototype.toString();
                // return beanPrototype().toString();
            }
        };
    }

    @Bean
    public BeanFactoryPostProcessor bfppLogger() {
        return new LoggerBfpp();
    }
}
