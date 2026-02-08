package com.lokman.securityHandsDirtry.service;

import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;

public interface UserService {

	SecurityUserDTO findUserAuthDataByEmail(String email);	
}
