package com.asseco.aha.training.spring_advanced.core.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.asseco.aha.training.spring_advanced.core.wiring.Drink;
import com.asseco.aha.training.spring_advanced.core.wiring.bean.Beer;
import com.asseco.aha.training.spring_advanced.core.wiring.bean.Tea;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Bean
	public Drink tea() {
		return new Tea();
    }

    @Bean
	public Drink beer() {
		return new Beer();
    }

}
