package com.lokman.securityHandsDirtry.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Map<String, Object> handleUserNotfound(NotFoundException ex, HttpServletRequest request) {
		return Map.of("status", HttpStatus.NOT_FOUND,
				"error", "Not Found", 
				"message", ex.getMessage(),
				"path", request.getRequestURI());
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public Map<String, Object> handleUserNotfound(UnauthorizedException ex, HttpServletRequest request) {
		return Map.of("status", HttpStatus.UNAUTHORIZED,
				"error", "Unauthorized", 
				"message", ex.getMessage(),
				"path", request.getRequestURI());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleGenericError(Exception ex, HttpServletRequest request){
		return Map.of("status", HttpStatus.INTERNAL_SERVER_ERROR,
				 "error", "Internal Server Error",
				"message", "Something went wrong",
				"path", request.getRequestURI());
	}
	
	
}
