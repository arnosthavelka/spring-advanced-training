package com.github.aha.sat.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbApplication.class, args);
    }

//	@Bean
//	Validator beanValidation() {
//		return new LocalValidatorFactoryBean();
//	}
	
//    @Autowired
//    private MetricRegistry registry;

    /**
     * @see http://blog.frankel.ch/become-a-devops-with-spring-boot
     */
//    @Bean
//    public JmxReporter jmxReporter() {
//        JmxReporter reporter = JmxReporter.forRegistry(registry).inDomain("appMetrics").build();
//        reporter.start();
//        return reporter;
//    }
    
}
