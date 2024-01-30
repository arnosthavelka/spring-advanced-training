package com.github.aha.sat.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class BeverageLogger {

	@Pointcut("execution(* com.github.aha.sat..Tea.*())")
	private void teaUsage() {
		log.info("Tea drink is used ...");
	}

	@Before("teaUsage()")
	public void logTeaUsage(JoinPoint joinPoint) {
		log.info("Tea drink is being used by {} method ...", joinPoint.getSignature().getName());
	}

	@After("execution(* com.github.aha.sat.core.wiring.Drink.*())")
	public void logAnyDrinkUsage(JoinPoint joinPoint) {
		log.info("Drink instance was just used by {}", joinPoint.getTarget().getClass().getSimpleName());
	}

	@Around("execution(* com.github.aha.sat..*Controller.*(..))")
	public Object logController(ProceedingJoinPoint pjp) {
		Object value = null;
		try {
			value = pjp.proceed();
			log.info("Controller method '{}' returned '{}'", pjp.getSignature().getName(), value);
		}
		catch (Throwable e) {
			log.error("AOP around aspect error", e);
		}
		return value;
	}

}
