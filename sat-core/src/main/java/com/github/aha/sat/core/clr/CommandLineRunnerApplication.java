package com.github.aha.sat.core.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CommandLineRunnerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CommandLineRunnerApplication.class, args);
    }

    @Bean
	CommandLineRunner bye() {
        return new CommandLineRunner () {

			@Override
			public void run(String... args) throws Exception {
				log.info("Bye!");
			}
        	
		};
    }

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting ...");
	}

}
