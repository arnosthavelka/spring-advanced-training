package com.github.aha.sat.rest;

import static org.springframework.boot.Banner.Mode.OFF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger
public class RestApplication /* extends WebMvcConfigurerAdapter */{

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestApplication.class);
		app.setBannerMode(OFF);
        app.run(args);
    }

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*city.*");
    }

    private ApiInfo apiInfo() {
		return new ApiInfo("spring-advanced-rest", "Demonstration of REST in Spring Boot", null, "arnost.havelka@gmail.com", null, null);
    }

}
