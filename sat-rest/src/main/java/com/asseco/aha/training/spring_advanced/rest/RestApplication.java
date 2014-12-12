package com.asseco.aha.training.spring_advanced.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
// @EnableWebMvc
/**
 * Links for content negotiation:
 * http://hmkcode.com/spring-mvc-view-json-xml-pdf-or-excel/
 * http://spring.io/blog/2013/05/11/content-negotiation-using-spring-mvc/
 * https://spring.io/blog/2013/06/03/content-negotiation-using-views
 * http://www.baeldung.com/spring-httpmessageconverter-rest
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
}
