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
		.formLogin(form -> form.disable())
		// 403 handler
		.exceptionHandling(ex -> ex
	            // 403 handler
	            .accessDeniedHandler((request, response, ex1) -> {
	                response.setStatus(response.SC_FORBIDDEN);
	                response.setContentType("application/json");

	                String jsonResponse = "{"
	                        + "\"status\": 403,"
	                        + "\"error\": \"Forbidden\","
	                        + "\"message\": \"Access Denied: You do not have permission to view this resource\","
	                        + "\"path\": \"" + request.getRequestURI() + "\""
	                        + "}";
	                
	                response.getWriter().write(jsonResponse);
	            })
	            // 401 handler
	            .authenticationEntryPoint((request, response, ex2) -> {
	                response.setStatus(response.SC_UNAUTHORIZED);
	                response.setContentType("application/json");

	                String jsonResponse = "{"
	                        + "\"status\": 401,"
	                        + "\"error\": \"Unauthorized\","
	                        + "\"message\": \"You must authenticate before accessing this resource\","
	                        + "\"path\": \"" + request.getRequestURI() + "\""
	                        + "}";

	                response.getWriter().write(jsonResponse);
	            })
	        );

	    return http.build();
	    
	}
}
