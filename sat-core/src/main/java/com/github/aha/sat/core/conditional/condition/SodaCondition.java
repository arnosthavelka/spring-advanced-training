package com.github.aha.sat.core.conditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SodaCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata atm) {
		return context.getEnvironment().containsProperty("soda");
	}

}
