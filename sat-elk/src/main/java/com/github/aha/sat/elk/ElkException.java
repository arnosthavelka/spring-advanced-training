package com.github.aha.sat.elk;

public class ElkException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ElkException(String errorMessage) {
		super(errorMessage);
	}

	public ElkException(Throwable cause) {
		super(cause);
	}

}
