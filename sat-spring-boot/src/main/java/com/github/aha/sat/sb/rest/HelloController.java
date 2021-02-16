package com.github.aha.sat.sb.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.actuate.metrics.CounterService;
//import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.aha.sat.sb.dto.User;

/**
 * http://localhost:8080/user?id=333&name=Roksana
 * 
 * @see http://kielczewski.eu/2015/01/application-metrics-with-spring-boot-actuator/
 */
@RestController
public class HelloController {

//	@Autowired
//	private CounterService counterService;
//
//	@Autowired
//	private GaugeService gaugeService;

	@Value("${hello.name:Arny}")
    private String name;

	@GetMapping("/hello")
    String hello() {
		addMetric(name);
        return String.format("Hello %s!", name);
    }

	@GetMapping("/user")
	String helloUser(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return getErrorMessage(bindingResult);
		}
		String value = user.getName();
		addMetric(value);
		return String.format("Hello user %s [id=%d]!", value, user.getId());
	}

	private String getErrorMessage(BindingResult bindingResult) {
		StringBuffer sb = new StringBuffer();
		sb.append("Validation error [count=").append(bindingResult.getErrorCount()).append("]!<br/>");
		sb.append("--------------------------------<br/>");
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			sb.append("- attribute=").append(fieldError.getObjectName()).append(".").append(fieldError.getField());
			sb.append(", message=").append(fieldError.getDefaultMessage()).append("<br/>");
		}

		return sb.toString();
	}

	private void addMetric(String value) {
//		counterService.increment("hello." + value);
//		gaugeService.submit("hello.length", value.length());
	}

}
