package com.lokman.securityHandsDirtry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lokman.securityHandsDirtry.dto.AuthRequest;
import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;
import com.lokman.securityHandsDirtry.jwt.JwtUtil;
import com.lokman.securityHandsDirtry.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final UserService userService;

	private final JwtUtil jwtUtil;

	@Autowired
	public AuthController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(AuthRequest authRequest) {

		SecurityUserDTO userDTO = userService.findUserAuthDataByEmail(authRequest.getEmail());
	}
}
