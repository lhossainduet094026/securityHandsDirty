package com.lokman.securityHandsDirtry.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;
import com.lokman.securityHandsDirtry.dto.UserAuthRow;
import com.lokman.securityHandsDirtry.exceptions.NotFoundException;
import com.lokman.securityHandsDirtry.repository.UserRepository;
import com.lokman.securityHandsDirtry.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public SecurityUserDTO findUserAuthDataByEmail(String email) {

		List<UserAuthRow> userAuthDataByEmail = userRepository.findUserAuthDataByEmail(email);

		if (userAuthDataByEmail.isEmpty()) {
			throw new NotFoundException("User not found: " + email);
		}

		Set<String> roles = new HashSet<>();
		Set<String> authorities = new HashSet<>();

		userAuthDataByEmail.forEach(row -> {

			if (row.getRoleName() != null) {
				roles.add("ROLE_" + row.getRoleName());
			}

			if (row.getAuthorityName() != null) {
				authorities.add(row.getAuthorityName());
			}

		});

		UserAuthRow userData = userAuthDataByEmail.get(0);

		return new SecurityUserDTO(userData.getName(), userData.getPassword(), userData.getEmail(), roles, authorities);
	}

}
