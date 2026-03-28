package com.cg.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidLoanAmountException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAmount(InvalidLoanAmountException ex) {
		return new ResponseEntity<>(new ErrorResponse("InvalidLoanAmountException", ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateLoanApplicationException.class)
	public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateLoanApplicationException ex) {
		return new ResponseEntity<>(new ErrorResponse("DuplicateLoanApplicationException", ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(LoanNotFoundException ex) {
		return new ResponseEntity<>(new ErrorResponse("LoanNotFoundException", ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(LoanStatusException.class)
	public ResponseEntity<ErrorResponse> handleGeneric(LoanStatusException ex) {
		return new ResponseEntity<>(new ErrorResponse("LoanStatusException", ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		String message = errors.values().iterator().next();

		return new ResponseEntity<>(new ErrorResponse("InvalidLoanAmountException", message), HttpStatus.BAD_REQUEST);
	}
}
