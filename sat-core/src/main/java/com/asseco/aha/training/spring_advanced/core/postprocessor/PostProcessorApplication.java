package com.asseco.aha.training.spring_advanced.core.postprocessor;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
