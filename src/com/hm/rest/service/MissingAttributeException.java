package com.hm.rest.service;

public class MissingAttributeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2066814697790868512L;

	public MissingAttributeException() {
		super();

	}

	public MissingAttributeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public MissingAttributeException(String message, Throwable cause) {
		super(message, cause);

	}

	public MissingAttributeException(String message) {
		super(message);

	}

	public MissingAttributeException(Throwable cause) {
		super(cause);

	}
}
