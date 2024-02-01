package com.github.aha.sat.core.postprocessor;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostProcessorConfig {

    @Bean
    BeanFactoryPostProcessor bfppLogger() {
        return new LoggerBfpp();
    }

    @Bean
    BeanPostProcessor upperCaseBpp() {
        return new UpperCaseBpp();
    }

    @Bean
    String crown() {
        return "One who wears the crown, bears the crown.";
    }

}
