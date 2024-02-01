package com.github.aha.sat.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Usage:
 *
 * access UI - GET http://localhost:8080/swagger-ui.html Swagger API - GET
 * http://localhost:8080/v3/api-docs
 *
 * @see https://www.baeldung.com/spring-rest-openapi-documentation
 * @see https://springdoc.org/#migrating-from-springfox
 */
@Configuration
public class SpringdocOpenapiConfig {

    @Bean
    OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("SAT-REST API")
            .description("Usage of Springdoc Openapi in Spring Boot")
            .version("0.6.1")
            .license(new License().name("License MIT")
                .url("https://github.com/arnosthavelka/spring-advanced-training/blob/master/LICENSE")));
    }

}
