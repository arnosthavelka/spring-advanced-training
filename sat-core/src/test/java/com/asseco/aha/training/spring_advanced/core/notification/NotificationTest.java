package com.asseco.aha.training.spring_advanced.core.notification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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
