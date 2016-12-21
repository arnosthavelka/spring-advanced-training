package com.github.aha.sat.sb;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;

@SpringBootApplication
public class SbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbApplication.class, args);
    }

	@Bean
	Validator beanValidation() {
		return new LocalValidatorFactoryBean();
	}
	
    @Autowired
    private MetricRegistry registry;

    /**
     * @see http://blog.frankel.ch/become-a-devops-with-spring-boot
     */
    @Bean
    public JmxReporter jmxReporter() {
        JmxReporter reporter = JmxReporter.forRegistry(registry).inDomain("appMetrics").build();
        reporter.start();
        return reporter;
    }
    
}
