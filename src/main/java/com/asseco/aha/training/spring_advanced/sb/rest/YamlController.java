package com.asseco.aha.training.spring_advanced.sb.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YamlController {

    // http://localhost:8080/yaml/value
    @Value("${yaml.foo.value}")
    private String value;

    // http://localhost:8080/yaml/name
    @Value("${yaml.foo.name}")
    private String fname;

    // http://localhost:8080/yaml/desc
    @Value("${yaml.desc}")
    private String desc;

    @RequestMapping("/yaml/{name}")
    public String value(@PathVariable("name") String name) {
        if ("value".equals(name)) {
            return value;
        } else if ("name".equals(name)) {
            return fname.toString();
        } else if ("desc".equals(name)) {
            return desc.toString();
        } else {
            return "Unknown parameter!";
        }
    }

}
