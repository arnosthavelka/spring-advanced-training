package com.github.aha.sat.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    @GetMapping(value = "hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {

        if (StringUtils.hasText(name) && !"unknown".equals(name)) {
            model.addAttribute("name", name);
            return "hello";
        }

        throw new IllegalArgumentException("Name not found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNotFoundException() {
        // do some handling logic
        return "hello";
    }

}
