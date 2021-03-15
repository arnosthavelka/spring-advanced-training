package com.github.aha.sat.rest.config;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Usage:
 * 
 * access UI 	- GET http://localhost:8080/swagger-ui/
 * Swagger API	- GET http://localhost:8080/v2/api-docs
 * 
 * @see https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
 */
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.github.aha.sat.rest"))
				.paths(PathSelectors.ant("/city/**"))
//				.paths(PathSelectors.ant("/stat/*"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"SAT-REST API",
				"Usage of Swagger in Spring Boot",
				"2.0",
				null,
				new Contact("SAT-REST on GitHub", "https://github.com/arnosthavelka/spring-advanced-training/", "arnost.havelka@gmail.com"),
				"License MIT", "https://github.com/arnosthavelka/spring-advanced-training/blob/master/LICENSE", Collections.emptyList());
	}

}
