package com.nit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuestionExcetiption {
	
	@ExceptionHandler(NoQuetionFoundException.class)
	public ResponseEntity<String> NoQuestionFoundException(
			NoQuetionFoundException ex
			){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> HandleBadRequest(
			IllegalArgumentException ex
			){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> Exception(
			Exception ex
			){
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ex.getMessage());
	}
}
