package com.asseco.aha.training.spring_advanced.core.notification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NotificationApplication.class)
public class NotificationTest {

    @Autowired
	private ApplicationContext ctx;

    @Test
    public void contextLoads() {
		ctx.publishEvent(new CustomEvent(ctx, "First message"));
		ctx.publishEvent(new CustomEvent(ctx, "Second message"));
    }
}
