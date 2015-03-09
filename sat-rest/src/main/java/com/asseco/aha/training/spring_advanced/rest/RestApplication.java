package com.asseco.aha.training.spring_advanced.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.core.DefaultRelProvider;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger
/**
 * #content negotiation:
 * http://hmkcode.com/spring-mvc-view-json-xml-pdf-or-excel/
 * http://spring.io/blog/2013/05/11/content-negotiation-using-spring-mvc/
 * https://spring.io/blog/2013/06/03/content-negotiation-using-views
 * http://www.baeldung.com/spring-httpmessageconverter-rest
 * #swagger
 * http://java.dzone.com/articles/how-configure-swagger-generate
 * http://www.dzone.com/links/r/spring_rest_api_with_swagger_exposing_documentati.html
 */
public class RestApplication /* extends WebMvcConfigurerAdapter */{

    public static void main(String[] args) {
        // SpringApplication.run(RestApplication.class, args);
        SpringApplication app = new SpringApplication(RestApplication.class);
        app.setShowBanner(false);
        app.run(args);
    }

    // @Override
    // public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    // configurer.favorPathExtension(false).favorParameter(true).parameterName("mediaType").defaultContentType(MediaType.APPLICATION_JSON)
    // .mediaType("xml", MediaType.APPLICATION_XML).mediaType("json", MediaType.APPLICATION_JSON);
    // }
    //

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

	// swagger configuration

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*city.*");
    }

    private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("spring-advanced-rest", "Demonstration of REST in Spring Boot", null,
				"arnost.havelka@gmail.com",
                null, null);
        return apiInfo;
    }

	// HAL configuration for JSON serialization

	@Bean
    RelProvider relProvider() {
        return new RelProvider()  {
        	
        	DefaultRelProvider defaultRelProvider = new DefaultRelProvider();

			private String getJsonRootName(Class<?> type) {
				JsonRootName[] annotations = type.getAnnotationsByType(JsonRootName.class);
				return annotations == null ? defaultRelProvider.getCollectionResourceRelFor(type) : annotations[0]
						.value();
			}

			@Override
			public boolean supports(Class<?> type) {
				return defaultRelProvider.supports(type);
			}

			@Override
			public String getItemResourceRelFor(Class<?> type) {
				return getJsonRootName(type);
			}

			@Override
			public String getCollectionResourceRelFor(Class<?> type) {
				String rootName = getJsonRootName(type);
				if (rootName.toLowerCase().endsWith("y")) {
					// really simple solution
					rootName = rootName.subSequence(0, rootName.length() - 1) + "ie";
				}
				return rootName + "s";
			}
		};
    }
}
