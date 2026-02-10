package com.lokman.securityHandsDirtry.dto;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecurityUserDTO {

	private String name;

	private String password;
	
	private String email;

	private Set<String> roles;
	
	public Set<String> authorities;

	public SecurityUserDTO(String username, String password, String email, Set<String> roles, Set<String> authorities) {
		this.name = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.authorities = authorities;
	}
}
