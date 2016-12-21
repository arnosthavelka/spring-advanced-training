package com.github.aha.sat.core.testing.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.aha.sat.core.config.User;

@Configuration
public class ProfileConfig {

    @Autowired
    private User user;

    @Bean
    public User user() {
        return user;
    }

}
