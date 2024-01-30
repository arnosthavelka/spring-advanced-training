package com.github.aha.sat.core.testing.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.aha.sat.core.config.User;

@Configuration
@Profile("barbara")
public class BarbaraConfig {

	@Bean
	User user() {
		return new User("Barbara");
	}

}
