package com.github.aha.sat.core.clr;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
public class CommandLineRunnerApplicationTest {

	@Autowired
	CommandLineRunner bye;

	@Test
	void run(CapturedOutput output) throws Exception {
		assertThat(output.getOut()).contains("Starting ...").contains("Bye!");
	}

}
