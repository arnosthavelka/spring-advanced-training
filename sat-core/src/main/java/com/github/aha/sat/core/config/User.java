package com.github.aha.sat.core.config;

import org.springframework.stereotype.Component;

@Component
public class User {

    private String name;

    // @Autowired
    public User(/* @Value("") */String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
