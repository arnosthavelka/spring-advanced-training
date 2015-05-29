package com.asseco.aha.training.spring_advanced.sb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see http://kielczewski.eu/2015/01/application-metrics-with-spring-boot-actuator/
 */
@RestController
public class HelloController {

	@Autowired
	private CounterService counterService;

	@Autowired
	private GaugeService gaugeService;

	@Value("${hello.name:Arny}")
    private String name;

    @RequestMapping("/hello")
    String hello() {
		counterService.increment("hello." + name);
		gaugeService.submit("hello.length", name.length());
        return String.format("Hello %s!", name);
    }

}
