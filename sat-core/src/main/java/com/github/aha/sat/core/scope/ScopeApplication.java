package com.github.aha.sat.core.scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;
import static org.springframework.context.annotation.ScopedProxyMode.INTERFACES;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@ComponentScan("com.github.aha.sat.core.scope")
public class ScopeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScopeApplication.class, args);
    }

    @Bean
	@Scope(value = SCOPE_PROTOTYPE, proxyMode = INTERFACES)
    public TokenBean beanPrototype() {
//    	return () -> UUID.randomUUID().toString();
		return new TokenBean() {
			@Override
			public String getToken() {
				return UUID.randomUUID().toString();
			}
		};
    }

    @Bean
    public TokenBean beanSingleton(final TokenBean beanPrototype) {
		return beanPrototype::toString;
    }

}
