package com.lokman.securityHandsDirtry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

	@Bean
	public UserDetailsService userDetailService() {
		UserDetails lokman = User.builder().username("lokman")
				.password("lokman")
				.roles("ADMIN")
				.build();
		
		UserDetails rupta = User.builder().username("rupta")
				.password("rupta")
				.roles("CALLCENTER")
				.build();
		
		UserDetails asik = User.builder().username("ashik")
				.password("ashik")
				.build();
		
		return new InMemoryUserDetailsManager(lokman, rupta, asik);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
