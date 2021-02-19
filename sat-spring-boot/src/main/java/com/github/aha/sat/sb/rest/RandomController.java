package com.github.aha.sat.sb.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomController {

	// http://localhost:8080/random/value
	@Value("${my.value}")
	private String value;

	// http://localhost:8080/random/number
	@Value("${my.number}")
	private Integer number;

	// http://localhost:8080/random/long
	@Value("${my.long}")
	private Long longnumber;

	// http://localhost:8080/random/int10
	@Value("${my.number.less.than.ten}")
	private Integer int10;

	// http://localhost:8080/random/intRange
	@Value("${my.number.in.range}")
	private Integer intRange;

	@GetMapping("/random/{name}")
	public String value(@PathVariable("name") String name) {
		if ("value".equals(name)) {
			return value;
		}
		if ("number".equals(name)) {
			return number.toString();
		}
		if ("long".equals(name)) {
			return longnumber.toString();
		}
		if ("int10".equals(name)) {
			return int10.toString();
		}
		if ("intRange".equals(name)) {
			return intRange.toString();
		}
		return "Unknown parameter!";
	}

}
