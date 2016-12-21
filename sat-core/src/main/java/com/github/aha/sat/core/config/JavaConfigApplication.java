package com.github.aha.sat.core.config;

import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = { "com.github.aha.sat.core.testing" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class))
public class JavaConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaConfigApplication.class, args);
    }

    @Bean
    public String hi() {
        return "Hi all!";
    }

    @Bean
    public String hello() {
        return "Hello all!";
    }

    @Bean
    public String randomGreeting() {
        int val = new Random().nextInt(10) % 2;
        return val == 0 ? hi() : hello();
    }

    @Bean
    public String hiGreeting(String hi) {
        return hi;
    }

    @Bean
    public String helloGreeting(@Qualifier("hello") String bean) {
        return bean;
    }

    @Bean
    public User userArny() {
        return new User("Arny");
    }

}
