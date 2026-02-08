package com.lokman.securityHandsDirtry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class UserAuthRow {
	private String name;
	private String password;
	private String email;
	private String roleName; 
	private String authorityName;
}
