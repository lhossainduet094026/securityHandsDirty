package com.lokman.securityHandsDirtry.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final JwtConfig jwtConfig;

	@Autowired
	public JwtUtil(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	public String generateToken(SecurityUserDTO user) {

		Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles());
        
        return Jwts.builder().setClaims(claims)
        .setSubject(user.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
        .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
        .compact();
		
	}
	
	public String extractUserName(String token) {
		
		return Jwts.parser().setSigningKey(jwtConfig.getSecret())
		.parseClaimsJws(token)
		.getBody()
		.getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		
		return false;
	} 
	
	
}
