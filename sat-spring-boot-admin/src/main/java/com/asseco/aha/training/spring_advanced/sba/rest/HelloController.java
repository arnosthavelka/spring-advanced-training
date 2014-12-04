package com.asseco.aha.training.spring_advanced.sba.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${hello.name:Arny}")
    private String name;

    @RequestMapping("/hello")
    String hello() {
        return String.format("Hello %s!", name);
    }

}
