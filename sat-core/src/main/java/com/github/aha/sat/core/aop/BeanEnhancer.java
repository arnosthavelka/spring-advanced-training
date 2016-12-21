package com.github.aha.sat.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeanEnhancer {

	@DeclareParents(value = "com.asseco.aha.training.spring_advanced.core.aop.EnjoyableDrink+", defaultImpl = EnjoyableImpl.class)
	public static Enjoyable enjoyable;

}
