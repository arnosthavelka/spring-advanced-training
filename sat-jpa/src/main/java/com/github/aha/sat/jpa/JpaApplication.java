package com.github.aha.sat.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

	/**
	 * Usage: http://localhost:8080/console/ (JDBC URL=jdbc:h2:mem:testdb)
	 * 
	 * @see https://dzone.com/articles/using-the-h2-database-console-in-spring-boot-with
	 */
//	@Bean
//	ServletRegistrationBean<WebServlet> h2servletRegistration() {
//		var registrationBean = new ServletRegistrationBean<WebServlet>(new WebServlet());
//		registrationBean.addUrlMappings("/console/*");
//		return registrationBean;
//	}
}
