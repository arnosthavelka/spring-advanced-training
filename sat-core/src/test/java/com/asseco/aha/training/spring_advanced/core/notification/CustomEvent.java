package com.asseco.aha.training.spring_advanced.core.notification;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private String msg;

	public CustomEvent(Object source, final String msg) {
		super(source);
		this.msg = msg;
	}

	@Override
	public String toString() {
		return String.format("CustomEvent message: '%s'", this.msg);
	}
}
