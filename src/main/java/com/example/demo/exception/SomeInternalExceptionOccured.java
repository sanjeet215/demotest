package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SomeInternalExceptionOccured extends RuntimeException {

	private static final long serialVersionUID = 1743031404175078942L;

	public SomeInternalExceptionOccured() {
		super();
	}

	public SomeInternalExceptionOccured(String message) {
		super(message);
	}

	public SomeInternalExceptionOccured(String message, Throwable cause) {
		super(message, cause);
	}

}
