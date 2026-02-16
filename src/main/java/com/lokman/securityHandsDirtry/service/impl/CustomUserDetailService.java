package com.lokman.securityHandsDirtry.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;
import com.lokman.securityHandsDirtry.exceptions.NotFoundException;
import com.lokman.securityHandsDirtry.service.UserService;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private final UserService userService;

	@Autowired
	public CustomUserDetailService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		SecurityUserDTO securityUserDTO = userService.findUserAuthDataByEmail(email);

		if (securityUserDTO == null) {
			throw new NotFoundException("User not found with email:" + email);
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		if (!securityUserDTO.getRoles().isEmpty()) {
			securityUserDTO.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));
		}

		if (!securityUserDTO.getAuthorities().isEmpty()) {
			securityUserDTO.getAuthorities().forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority)));
		}

		return new User(securityUserDTO.getEmail(), securityUserDTO.getPassword(), grantedAuthorities);
	}

}
