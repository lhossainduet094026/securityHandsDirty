package com.lokman.securityHandsDirtry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

	@GetMapping("/")
	public String home() {
		return "Welcome to the Web Shop! (All authenticated users can see this)";
	}

	@GetMapping("/products")
	public String products() {
		return "List of products visible to any authenticated user.";
	}

}
