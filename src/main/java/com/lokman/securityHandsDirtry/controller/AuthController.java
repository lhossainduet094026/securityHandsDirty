package com.lokman.securityHandsDirtry.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lokman.securityHandsDirtry.dto.AuthRequest;
import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;
import com.lokman.securityHandsDirtry.exceptions.UnauthorizedException;
import com.lokman.securityHandsDirtry.jwt.JwtUtil;
import com.lokman.securityHandsDirtry.service.UserService;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final UserService userService;

	private final PasswordEncoder passwordEncoder;

	private final JwtUtil jwtUtil;

	@Autowired
	public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@NotNull @RequestBody AuthRequest authRequest) {

		SecurityUserDTO userDTO = userService.findUserAuthDataByEmail(authRequest.getEmail());

		if (userDTO == null || userDTO.getEmail() == null)
			throw new UsernameNotFoundException("User not found!");

		if (!passwordEncoder.matches(authRequest.getPassword(), userDTO.getPassword())) {
			throw new UnauthorizedException("Invalid credentials");
		}

		String token = jwtUtil.generateToken(userDTO);

		Map<String, Object> successResponse = Map.of("token", token, 
				"success", true,
				"status", HttpStatus.OK,
				"message", "token generated successful");

		return new ResponseEntity<Map<String, Object>>(successResponse, HttpStatus.OK);

	}
}
