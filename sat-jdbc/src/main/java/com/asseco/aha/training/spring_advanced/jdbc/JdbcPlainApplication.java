package com.asseco.aha.training.spring_advanced.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.asseco.aha.training.spring_advanced.jdbc")
@EnableAutoConfiguration
public class JdbcPlainApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcPlainApplication.class, args);
    }

}
