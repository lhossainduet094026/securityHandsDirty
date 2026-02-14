package com.lokman.securityHandsDirtry.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

	@NotBlank(message = "Email can not be empty")
	@Email(message = "Invalid email")
	private String email;
	
	@NotBlank(message = "Password can not be blank")
	private String password;
}
