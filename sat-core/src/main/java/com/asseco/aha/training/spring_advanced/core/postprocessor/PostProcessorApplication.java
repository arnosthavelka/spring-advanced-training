package com.asseco.aha.training.spring_advanced.core.postprocessor;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.asseco.aha.training.spring_advanced.core.postprocessor")
@EnableAutoConfiguration
public class PostProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostProcessorApplication.class, args);
    }

    @Bean
    public BeanFactoryPostProcessor bfppLogger() {
        return new LoggerBfpp();
    }

    @Bean
    public BeanPostProcessor upperCaseBpp() {
        return new UpperCaseBpp();
    }

    @Bean
    public String hello() {
        return "Hi all!";
    }
}
