package com.asseco.aha.training.spring_advanced.core.testing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.asseco.aha.training.spring_advanced.core.config.User;

@Configuration
@Profile("barbara")
public class BarbaraConfig {

    @Bean
    public User userArny() {
        return new User("Barbara");
    }

}
