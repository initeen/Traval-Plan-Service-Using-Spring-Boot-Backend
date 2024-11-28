package com.proazure.travel.plan.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e){
		
		log.error("GlobalExceptionHander :: handleException ::", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e){
		
		log.error("GlobalExceptionHander :: handleResourceNotFoundException ::", e.getMessage());
		return new ResponseEntity<> (e.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException e){
		
		
		return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
	}
}
