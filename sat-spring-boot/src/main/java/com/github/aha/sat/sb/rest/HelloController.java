package com.github.aha.sat.sb.rest;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.aha.sat.sb.dto.User;

import io.micrometer.jmx.JmxMeterRegistry;

/**
 * http://localhost:8080/user?id=333&name=Roksana
 */
@RestController
public class HelloController {

	@Autowired
	private JmxMeterRegistry registry;

	@Value("${hello.name:Arny}")
    private String name;

	private AtomicInteger userLength = new AtomicInteger(0);

	@PostConstruct
	void init() {
		registry.gauge("hello.length", userLength);
	}

	@GetMapping("/hello")
    String hello() {
		countForName(name);
        return String.format("Hello %s!", name);
    }

	@GetMapping("/user")
	String helloUser(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return getErrorMessage(bindingResult);
		}
		String userName = user.getName();
		countForName(userName);
		userLength.set(userName.length());
		return String.format("Hello user %s [id=%d]!", userName, user.getId());
	}

	private String getErrorMessage(BindingResult bindingResult) {
		StringBuilder sb = new StringBuilder();
		sb.append("Validation error [count=").append(bindingResult.getErrorCount()).append("]!<br/>");
		sb.append("--------------------------------<br/>");
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			sb.append("- attribute=").append(fieldError.getObjectName()).append(".").append(fieldError.getField());
			sb.append(", message=").append(fieldError.getDefaultMessage()).append("<br/>");
		}

		return sb.toString();
	}

	private void countForName(String name) {
		registry.counter("hello." + name).increment();
	}

}
