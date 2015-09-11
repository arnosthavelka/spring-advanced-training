package com.asseco.aha.training.spring_advanced.core.testing.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.asseco.aha.training.spring_advanced.core.config.User;

@Configuration
public class ProfileConfig {

    @Autowired
    private User user;

    @Bean
    public User user() {
        return user;
    }

}
