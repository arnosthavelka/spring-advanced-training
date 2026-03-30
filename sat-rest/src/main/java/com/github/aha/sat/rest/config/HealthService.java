package com.github.aha.sat.rest.config;

import org.springframework.boot.health.contributor.AbstractHealthIndicator;
import org.springframework.boot.health.contributor.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class HealthService extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		builder.up().withDetail("data", "up to date");
	}

}