package com.github.aha.sat.core;

import static ch.qos.logback.classic.Level.DEBUG;
import static org.slf4j.Logger.ROOT_LOGGER_NAME;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LogUtils {

    public static ListAppender<ILoggingEvent> getAppenderForLoggerOfClass(Class<?> clazz) {
        Logger originalLogger = getLogger(clazz);
        return getAppender((ch.qos.logback.classic.Logger) originalLogger);
    }

    public static ListAppender<ILoggingEvent> getAppenderForRootLogger() {
        Logger originalLogger = getLogger(ROOT_LOGGER_NAME);
        return getAppender((ch.qos.logback.classic.Logger) originalLogger);
    }

    private static ListAppender<ILoggingEvent> getAppender(ch.qos.logback.classic.Logger originalLogger) {
        originalLogger.setLevel(DEBUG);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        originalLogger.addAppender(listAppender);
        return listAppender;
    }

}
