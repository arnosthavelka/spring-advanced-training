package com.github.aha.sat.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
        // it is also configurable in YML (as spring.main.banner-mode)
        app.setBannerMode(Mode.OFF);
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

}
