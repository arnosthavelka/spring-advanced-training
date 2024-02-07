package com.github.aha.sat.core.notification;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent ce) {
        log.info(ce.toString());
    }

}
