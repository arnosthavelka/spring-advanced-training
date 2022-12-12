package com.github.aha.sat.rest.city;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CityExceptionHandler extends ResponseEntityExceptionHandler {

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException exception,
//			@NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
//		return buildResponse(BAD_REQUEST, exception);
//	}

	@ExceptionHandler({ CityNotFoundException.class })
	public final ResponseEntity<Object> handleNotFoundException(Exception exception) {
		return buildResponse(NOT_FOUND, exception);
	}

	@ExceptionHandler({ CityValidationException.class })
	public final ResponseEntity<Object> handleValidationException(Exception exception) {
		return buildResponse(NOT_ACCEPTABLE, exception);
	}

	private ResponseEntity<Object> buildResponse(HttpStatus status, Exception exception) {
		return ResponseEntity
				.status(status)
				.body(exception.getMessage());
	}

}
