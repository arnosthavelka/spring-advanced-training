package com.github.aha.sat.core.scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;
import static org.springframework.context.annotation.ScopedProxyMode.INTERFACES;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ScopeConfig {

	@Bean
	@Scope(value = SCOPE_PROTOTYPE, proxyMode = INTERFACES)
	TokenBean beanPrototype() {
		return new TokenBean() {
			@Override
			public String getToken() {
				return UUID.randomUUID().toString();
			}
		};
	}

	@Bean
	TokenBean beanSingleton(final TokenBean beanPrototype) {
		return beanPrototype::toString;
	}

}
