package com.lokman.securityHandsDirtry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
//		http.authorizeHttpRequests(auth -> auth.requestMatchers("/**").hasAnyRole("USER"))
//		.httpBasic(Customizer.withDefaults())
//		.formLogin(form -> form.disable());
		
		/**
		 * 
		 * securing end point based on roles(Inmemory)
		 * 
		 */
		
		http.authorizeHttpRequests(auth -> 
		// Admin area
		auth.requestMatchers("/api/admin/**").hasRole("ADMIN")
		//callcenter aread
		.requestMatchers("/api/callcenter/**").hasAnyRole("ADMIN", "CALLCENTER")
		// Any other request → authenticated users only
		.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		.formLogin(form -> form.disable());

		return http.build();
	}
}
