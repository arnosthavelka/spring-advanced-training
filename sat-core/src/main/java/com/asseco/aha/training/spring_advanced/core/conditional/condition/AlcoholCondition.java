package com.asseco.aha.training.spring_advanced.core.conditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class AlcoholCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata atm) {
		return context.getEnvironment().containsProperty("alcohol");
	}

}
