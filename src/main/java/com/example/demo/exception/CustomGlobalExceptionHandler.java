package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.POIXMLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put("message", errors);

		return new ResponseEntity<>(body, headers, status);

	}

	@ExceptionHandler({ POIXMLException.class, NullPointerException.class,InvalidFormatException.class })
	public ResponseEntity<CustomErrorResponse> excelExccception(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errors.setMessage("Invlaid file. Please upload xslx file");

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> filenotFoundException(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		errors.setMessage(ex.getMessage());

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FileStorageException.class)
	public ResponseEntity<CustomErrorResponse> fileStorageException(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errors.setMessage(ex.getMessage());

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<CustomErrorResponse> accessDeniedException(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.FORBIDDEN.value());
		errors.setMessage(ex.getMessage());

		return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		errors.setMessage(ex.getMessage());

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<CustomErrorResponse> resourceAlreadyExistException(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.IM_USED.value());
		errors.setMessage(ex.getLocalizedMessage());

		return new ResponseEntity<>(errors, HttpStatus.IM_USED);

	}

	@ExceptionHandler(SomeInternalExceptionOccured.class)
	public ResponseEntity<CustomErrorResponse> someInternamException(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errors.setMessage(ex.getLocalizedMessage());

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UnauthorizedAccess.class)
	public ResponseEntity<CustomErrorResponse> unauthorizedAccessException(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.UNAUTHORIZED.value());
		errors.setMessage(ex.getMessage());

		return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
	}

}
