package com.lokman.securityHandsDirtry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/callcenter")
public class CallCenterController {

	@GetMapping("/dashboard")
	public String dashboard() {
		return "Welcome Callcenter Agent! (Admins can see this too)";
	}
}
