package com.lokman.securityHandsDirtry.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@PostMapping
	public String addProduct() {

		return "Product added successful";
	}

	@PutMapping
	public String updateProduct() {
		return "Product update successful";
	}

	@DeleteMapping
	public String deleteProduct() {
		return "Product deleted successful";
	}

	@GetMapping
	public List<Object> getProducts() {
		return new ArrayList<>();
	}
}
