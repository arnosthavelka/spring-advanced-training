package com.github.aha.sat.core.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class MapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapperApplication.class, args);
    }

    @Autowired
    private ObjectMapper objectMapper;
    
    @Value("classpath:/users.json")
    private Resource usersJsonResource;
    
    
	@Bean
    public List<UserDTO> jsonUsers() throws IOException {
        try(InputStream inputStream = usersJsonResource.getInputStream()) {
        	UserDTO[] payloadUsers = objectMapper.readValue(inputStream,UserDTO[].class);
        	return Collections.unmodifiableList(Arrays.asList(payloadUsers));
        }    	
    }
}
