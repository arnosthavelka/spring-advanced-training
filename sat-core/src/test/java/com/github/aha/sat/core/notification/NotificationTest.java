package com.github.aha.sat.core.notification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.aha.sat.core.notification.CustomEvent;
import com.github.aha.sat.core.notification.NotificationApplication;

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
