package com.asseco.aha.training.spring_advanced.core.clr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@SpringBootApplication
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommandLineRunnerApplication implements CommandLineRunner {

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(CommandLineRunnerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CommandLineRunnerApplication.class, args);
    }

    @Bean
	CommandLineRunner bye() {
        return new CommandLineRunner () {

			@Override
			public void run(String... args) throws Exception {
				LOG.info("Bye!");
				
			}
        	
		};
    }

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Starting ...");
	}

}
