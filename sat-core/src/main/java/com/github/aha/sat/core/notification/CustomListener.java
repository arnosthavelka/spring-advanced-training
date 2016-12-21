package com.github.aha.sat.core.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomListener implements ApplicationListener<CustomEvent> {

	private Logger LOG = LoggerFactory.getLogger(CustomListener.class);

	@Override
	public void onApplicationEvent(CustomEvent ce) {
		LOG.info(ce.toString());
	}

}
