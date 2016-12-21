package com.github.aha.sat.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.aha.sat.jdbc")
public class JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

}
