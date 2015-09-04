package com.asseco.aha.training.spring_advanced.core.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.asseco.aha.training.spring_advanced.core.wiring.WiringConfig;

@Configuration
@Import(WiringConfig.class)
@ComponentScan
public class AopConfig {

}
