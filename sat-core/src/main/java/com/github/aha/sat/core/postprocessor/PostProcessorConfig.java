package com.github.aha.sat.core.postprocessor;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostProcessorConfig {

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
