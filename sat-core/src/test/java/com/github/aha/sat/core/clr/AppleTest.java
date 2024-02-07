package com.github.aha.sat.core.clr;

import static ch.qos.logback.classic.Level.DEBUG;
import static ch.qos.logback.classic.Level.INFO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.github.aha.sat.core.LogUtils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class AppleTest {

    Apple apple = new Apple();

    @Test
    void run() throws Exception {
        ListAppender<ILoggingEvent> logAppender = LogUtils.getAppenderForLoggerOfClass(Apple.class);

        apple.init();
        apple.run();

        assertThat(logAppender.list).hasSize(2).anySatisfy(logEntry -> {
            assertThat(logEntry.getLevel()).isEqualTo(DEBUG);
            assertThat(logEntry.getFormattedMessage()).startsWith("Initializing Apple");
        }).anySatisfy(logEntry -> {
            assertThat(logEntry.getLevel()).isEqualTo(INFO);
            assertThat(logEntry.getFormattedMessage()).isEqualTo("Here's Apple runner");
        });
    }

}
