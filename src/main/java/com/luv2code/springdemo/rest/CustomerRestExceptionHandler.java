package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	
	// add an exception handler for CustomerNotFoundEXception
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {
		
		// create a customer error reponse
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		
		error.setMessage(exc.getMessage());
		
		error.setTimestamp(System.currentTimeMillis());
		
		// return a response entity
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// add an exception handler to catch generic exceptions
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {
		
		// create a customer error reponse
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		
		error.setMessage(exc.getMessage());
		
		error.setTimestamp(System.currentTimeMillis());
		
		// return a response entity
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
