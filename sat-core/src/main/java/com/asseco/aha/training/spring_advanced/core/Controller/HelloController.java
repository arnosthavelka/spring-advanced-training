package com.asseco.aha.training.spring_advanced.core.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	@RequestMapping(value = "hello/{name}", method = RequestMethod.GET)
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
