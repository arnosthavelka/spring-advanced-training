package com.github.aha.sat.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.aha.sat.dummy")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
