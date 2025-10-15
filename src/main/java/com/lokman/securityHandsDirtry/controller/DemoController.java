package com.lokman.securityHandsDirtry.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	//authentication/principal is needed for only name retrieval
	
	@GetMapping
	public String getName(Authentication Authentication) {
		return Authentication.getName() + " logged in";
	}
	
	@GetMapping("/info")
	public String getOthersInfo(Principal principal) {
		  return principal.getName();
	}
}
