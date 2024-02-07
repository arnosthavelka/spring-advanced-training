package com.github.aha.sat.core.mapper;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class MapperApplication {

    @Value("classpath:/users.json")
    private Resource usersJsonResource;

    @Bean
    List<UserDTO> jsonUsers(ObjectMapper objectMapper) throws IOException {
		try (var inputStream = usersJsonResource.getInputStream()) {
        	UserDTO[] payloadUsers = objectMapper.readValue(inputStream,UserDTO[].class);
        	return List.of(payloadUsers);
        }    	
    }

}
