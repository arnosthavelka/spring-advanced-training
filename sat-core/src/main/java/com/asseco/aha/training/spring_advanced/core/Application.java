package com.asseco.aha.training.spring_advanced.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.asseco.aha.training.spring_advanced.dummy")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
