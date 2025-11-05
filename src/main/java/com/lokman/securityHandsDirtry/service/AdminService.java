package com.lokman.securityHandsDirtry.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAdminData(Long id) {
        // delete logic (DB call, etc.)
        System.out.println("Deleting admin data with ID: " + id);
    }
}
