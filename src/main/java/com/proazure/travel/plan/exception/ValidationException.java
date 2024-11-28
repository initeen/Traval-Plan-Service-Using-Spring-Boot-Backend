package com.proazure.travel.plan.exception;

import java.util.Map;

public class ValidationException extends RuntimeException {

	public Map<String, Object> errors;

	public ValidationException(Map<String, Object> errors) {
		super();
		this.errors = errors;
	}
	
	public Map<String, Object> getErrors(){
		
		return errors;
	}
}
