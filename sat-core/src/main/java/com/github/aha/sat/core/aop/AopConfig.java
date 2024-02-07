package com.github.aha.sat.core.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.aha.sat.core.wiring.beverage.Beverage;

@Configuration
public class AopConfig {

    @Bean
    Beverage milk() {
        return () -> "Milk";
    }

    @Bean
    EnjoyableBeverage hotChocolade() {
        return () -> "Hot Chocolade";
    }

}
