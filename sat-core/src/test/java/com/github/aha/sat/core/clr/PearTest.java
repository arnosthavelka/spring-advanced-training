package com.github.aha.sat.core.clr;

import static ch.qos.logback.classic.Level.DEBUG;
import static ch.qos.logback.classic.Level.INFO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.github.aha.sat.core.LogUtils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class PearTest {
	
	Pear pear = new Pear();
	
	@Test
	void run() throws Exception {
		ListAppender<ILoggingEvent> logAppender = LogUtils.getAppenderForLoggerOfClass( Pear.class );
		
		pear.init();
		assertThat( logAppender.list )
			.singleElement()
			.satisfies(logEntry -> {
				assertThat( logEntry.getLevel() ).isEqualTo(DEBUG);
				assertThat( logEntry.getFormattedMessage() ).isEqualTo("Initializing Pear bean ..." );
			});

		pear.run();
		assertThat( logAppender.list )
			.last()
			.satisfies(logEntry -> {
				assertThat( logEntry.getLevel() ).isEqualTo(INFO);
				assertThat( logEntry.getFormattedMessage() ).isEqualTo("Here's Pear runner" );
			});
	}

}
