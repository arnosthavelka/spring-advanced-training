package com.github.aha.sat.core.notification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = NotificationApplication.class)
public class NotificationTest {

    @Autowired
	private ApplicationContext ctx;

    @Test
    public void contextLoads() {
		ctx.publishEvent(new CustomEvent(ctx, "First message"));
		ctx.publishEvent(new CustomEvent(ctx, "Second message"));
    }
}
