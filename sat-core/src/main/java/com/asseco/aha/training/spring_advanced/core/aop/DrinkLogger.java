package com.asseco.aha.training.spring_advanced.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DrinkLogger {

	private static final Logger LOG = LoggerFactory.getLogger(DrinkLogger.class);

	@Pointcut("execution(* com.asseco.aha.training..Tea.*())")
	private void teaUsage() {

	}

	@Before("teaUsage()")
	public void logTeaUsage(JoinPoint joinPoint) {
		LOG.info("Tea drink is being used...");
	}

	@After("execution(* com.asseco.aha.training.spring_advanced.core.wiring.Drink.*())")
	public void logAnyDrinkUsage(JoinPoint jp) {
		LOG.info("Drink {} was just used...", jp.getTarget().getClass().getSimpleName());
	}

	@Around("execution(* com.asseco.aha.training..*Controller.*(..))")
	public Object logController(ProceedingJoinPoint pjp) {
		Object value = null;
		try {
			value = pjp.proceed();
			LOG.info("Controller method '{}' returned '{}'", pjp.getSignature().getName(), value);
		} catch (Throwable e) {
			LOG.error("AOP around aspect error", e);
		}
		return value;
	}
}
