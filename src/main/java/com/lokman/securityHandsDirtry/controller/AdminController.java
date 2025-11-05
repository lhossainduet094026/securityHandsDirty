package com.lokman.securityHandsDirtry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lokman.securityHandsDirtry.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/panel")
    public String adminPanel() {
        return "Welcome Admin! You can manage everything here.";
    }
	
	@DeleteMapping("/delete/{id}")
    public String deleteAdminData(@PathVariable Long id) {
        adminService.deleteAdminData(id); // secured at service layer
        return "Admin data with ID " + id + " deleted successfully.";
    }
}
