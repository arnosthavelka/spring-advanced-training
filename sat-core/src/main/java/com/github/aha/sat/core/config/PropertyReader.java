package com.github.aha.sat.core.config;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
@Scope(value = SCOPE_PROTOTYPE)
public class PropertyReader {

	@Value("${custom.value}")
	private String value;

	@Value("${custom.number}")
	private Integer number;

	@Value("${custom.long}")
	private Long longnumber;

	@Value("${custom.number.less.than.ten}")
	private Integer int10;

	@Value("${custom.number.in.range}")
	private Integer intRange;

}
