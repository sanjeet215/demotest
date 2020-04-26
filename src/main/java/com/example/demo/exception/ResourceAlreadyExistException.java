package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class ResourceAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 949338193013510509L;

	public ResourceAlreadyExistException() {
		super();
	}

	public ResourceAlreadyExistException(String message) {
		super(message);
	}

	public ResourceAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
