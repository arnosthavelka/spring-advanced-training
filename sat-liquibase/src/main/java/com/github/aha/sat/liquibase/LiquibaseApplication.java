package com.github.aha.sat.liquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.aha.sat.liquibase")
public class LiquibaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiquibaseApplication.class, args);
    }

}
