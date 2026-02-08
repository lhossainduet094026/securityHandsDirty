package com.lokman.securityHandsDirtry.common;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public Map<String, Object> handleUserNotfound(NotFoundException ex, HttpServletRequest request) {
		return Map.of("status", 401,
				"error", "Unauthorized", 
				"message", ex.getMessage(),
				"path", request.getRequestURI());
	}
}
