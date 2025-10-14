package com.lokman.securityHandsDirtry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping
	public String getName() {
		return "lokman logged in";
	}
}
