package com.github.aha.sat.war;

import javax.websocket.server.PathParam;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see http://localhost:8080/spring-advanced-war/greeting
 */
@RestController
public class GreetingController {

    /*
     * http://localhost:8080/city/, http://localhost:8080/city/?country=Spain, http://localhost:8080/city/?sorting=id
     */
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String list(@PathParam("name") String name) {
		if (StringUtils.isEmpty(name)) {
			name = "Anonymous";
		}
		return String.format("Hello %s!", name);
    }

}